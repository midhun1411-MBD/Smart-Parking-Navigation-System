package com.smartparking.service;

import com.smartparking.model.User;
import com.smartparking.model.ParkingSlot;
import com.smartparking.model.Booking;
import com.smartparking.repository.UserRepository;
import com.smartparking.repository.ParkingSlotRepository;
import com.smartparking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ParkingService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ParkingSlotRepository slotRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Value("${parking.total.slots:50}")
    private int totalSlots;
    
    private Random random = new Random();
    
    @PostConstruct
    public void initializeParkingSlots() {
        if (slotRepository.count() == 0) {
            for (int i = 1; i <= totalSlots; i++) {
                ParkingSlot slot = new ParkingSlot(i);
                slotRepository.save(slot);
            }
            System.out.println("Initialized " + totalSlots + " parking slots");
        }
    }
    
    // User Management
    public User registerUser(String userId, String password, String name) {
        if (userRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("User ID already exists");
        }
        User user = new User(userId, password, name);
        return userRepository.save(user);
    }
    
    public User loginUser(String userId, String password) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return user;
    }
    
    // Slot Management
    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }
    
    public long getAvailableSlotCount() {
        return slotRepository.countByIsOccupied(false);
    }
    
    public ParkingSlot findNearestSlot() {
        List<ParkingSlot> availableSlots = slotRepository.findByIsOccupied(false);
        if (availableSlots.isEmpty()) {
            return null;
        }
        return availableSlots.get(0);
    }
    
    // Booking Management
    @Transactional
    public Booking bookSlot(String userId, String vehicleId, Integer slotId, Double paymentAmount) {
        // Verify user exists
        if (!userRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        
        // Check if vehicle already parked
        Optional<ParkingSlot> existingVehicle = slotRepository.findByVehicleId(vehicleId);
        if (existingVehicle.isPresent()) {
            throw new IllegalStateException("Vehicle already parked in slot " + existingVehicle.get().getSlotId());
        }
        
        // Get the slot
        Optional<ParkingSlot> slotOpt = slotRepository.findBySlotId(slotId);
        if (slotOpt.isEmpty()) {
            throw new IllegalArgumentException("Slot not found");
        }
        
        ParkingSlot slot = slotOpt.get();
        if (slot.getIsOccupied()) {
            throw new IllegalStateException("Slot is already occupied");
        }
        
        // Park vehicle
        slot.parkVehicle(vehicleId);
        slotRepository.save(slot);
        
        // Create booking
        String bookingId = "B" + (bookingRepository.count() + 1);
        Booking booking = new Booking(bookingId, userId, vehicleId, slotId, paymentAmount);
        return bookingRepository.save(booking);
    }
    
    @Transactional
    public double exitParking(String vehicleId) {
        Optional<ParkingSlot> slotOpt = slotRepository.findByVehicleId(vehicleId);
        if (slotOpt.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        
        ParkingSlot slot = slotOpt.get();
        double payment = calculatePayment(slot.getEntryTime());
        
        slot.removeVehicle();
        slotRepository.save(slot);
        
        return payment;
    }
    
    public double calculatePayment(LocalDateTime entryTime) {
        if (entryTime == null) {
            return 0.0;
        }
        long hours = Duration.between(entryTime, LocalDateTime.now()).toHours();
        if (hours == 0) {
            hours = 1; // Minimum 1 hour
        }
        return hours * 2.0; // $2 per hour
    }
    
    // Booking History
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    // Simulation
    @Transactional
    public void simulateSlotDetection() {
        List<ParkingSlot> slots = slotRepository.findAll();
        for (ParkingSlot slot : slots) {
            if (!slot.getIsOccupied() && random.nextDouble() < 0.2) {
                slot.parkVehicle("SIM-" + slot.getSlotId());
                slotRepository.save(slot);
            }
        }
    }
}
