package com.smartparking.controller;

import com.smartparking.model.User;
import com.smartparking.model.ParkingSlot;
import com.smartparking.model.Booking;
import com.smartparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ParkingController {
    
    @Autowired
    private ParkingService parkingService;
    
    // User Management Endpoints
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        try {
            String userId = request.get("userId");
            String password = request.get("password");
            String name = request.get("name");
            
            User user = parkingService.registerUser(userId, password, name);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("user", user);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        try {
            String userId = request.get("userId");
            String password = request.get("password");
            
            User user = parkingService.loginUser(userId, password);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("user", user);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    // Slot Management Endpoints
    @GetMapping("/slots")
    public ResponseEntity<?> getAllSlots() {
        try {
            List<ParkingSlot> slots = parkingService.getAllSlots();
            long availableCount = parkingService.getAvailableSlotCount();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("slots", slots);
            response.put("availableCount", availableCount);
            response.put("totalSlots", slots.size());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/slots/nearest")
    public ResponseEntity<?> findNearestSlot() {
        try {
            ParkingSlot slot = parkingService.findNearestSlot();
            
            Map<String, Object> response = new HashMap<>();
            if (slot != null) {
                response.put("success", true);
                response.put("slot", slot);
            } else {
                response.put("success", false);
                response.put("message", "No available slots");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/slots/simulate")
    public ResponseEntity<?> simulateSlotDetection() {
        try {
            parkingService.simulateSlotDetection();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Slot detection simulated");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Booking Endpoints
    @PostMapping("/bookings")
    public ResponseEntity<?> bookSlot(@RequestBody Map<String, Object> request) {
        try {
            String userId = (String) request.get("userId");
            String vehicleId = (String) request.get("vehicleId");
            Integer slotId = Integer.parseInt(request.get("slotId").toString());
            Double paymentAmount = Double.parseDouble(request.get("paymentAmount").toString());
            
            Booking booking = parkingService.bookSlot(userId, vehicleId, slotId, paymentAmount);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Slot booked successfully");
            response.put("booking", booking);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @PostMapping("/exit")
    public ResponseEntity<?> exitParking(@RequestBody Map<String, String> request) {
        try {
            String vehicleId = request.get("vehicleId");
            double payment = parkingService.exitParking(vehicleId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Vehicle exited successfully");
            response.put("payment", payment);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        try {
            List<Booking> bookings = parkingService.getAllBookings();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("bookings", bookings);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<?> getUserBookings(@PathVariable String userId) {
        try {
            List<Booking> bookings = parkingService.getBookingsByUser(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("bookings", bookings);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
