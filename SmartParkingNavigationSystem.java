// File: SmartParkingNavigationSystem.java
// Save this code in a file named exactly "SmartParkingNavigationSystem.java"
// Compile: javac SmartParkingNavigationSystem.java
// Run: java SmartParkingNavigationSystem
// DO NOT run "java User" as it has no main method

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SmartParkingNavigationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total number of parking slots: ");
        int totalSlots;
        try {
            totalSlots = scanner.nextInt();
            if (totalSlots <= 0) {
                System.out.println("Number of slots must be positive.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Exiting.");
            scanner.close();
            return;
        }
        scanner.nextLine(); // Consume newline

        SmartParkingSystem parkingSystem = new SmartParkingSystem(totalSlots);
        User loggedInUser = null;

        while (true) {
            System.out.println("\nSmart Parking Navigation System Menu:");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. Simulate Slot Detection");
            System.out.println("4. Find Nearest Slot");
            System.out.println("5. Book Slot");
            System.out.println("6. Exit Vehicle");
            System.out.println("7. Display Parking Status");
            System.out.println("8. Display Booking History");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            try {
                switch (choice) {
                    case 1: // Register User
                        System.out.print("Enter User ID: ");
                        String userId = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        parkingSystem.registerUser(userId, password, name);
                        break;

                    case 2: // Login User
                        System.out.print("Enter User ID: ");
                        userId = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        password = scanner.nextLine();
                        loggedInUser = parkingSystem.loginUser(userId, password);
                        break;

                    case 3: // Simulate Slot Detection
                        parkingSystem.simulateSlotDetection();
                        parkingSystem.displayStatus();
                        break;

                    case 4: // Find Nearest Slot
                        int slotId = parkingSystem.findNearestSlot();
                        if (slotId == -1) {
                            System.out.println("No available slots.");
                        } else {
                            System.out.println("Nearest available slot: " + slotId);
                        }
                        break;

                    case 5: // Book Slot
                        if (loggedInUser == null) {
                            System.out.println("Please login first.");
                            break;
                        }
                        System.out.print("Enter Vehicle ID (e.g., ABC123): ");
                        String vehicleId = scanner.nextLine();
                        System.out.print("Enter Slot ID to book: ");
                        slotId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Payment Amount (e.g., 5.0): ");
                        double payment;
                        try {
                            payment = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                        } catch (Exception e) {
                            System.out.println("Invalid payment amount.");
                            scanner.nextLine(); // Clear invalid input
                            break;
                        }
                        parkingSystem.bookSlot(loggedInUser.getUserId(), vehicleId, slotId, payment);
                        break;

                    case 6: // Exit Vehicle
                        System.out.print("Enter Vehicle ID to exit: ");
                        vehicleId = scanner.nextLine();
                        parkingSystem.exitParking(vehicleId);
                        break;

                    case 7: // Display Parking Status
                        parkingSystem.displayStatus();
                        break;

                    case 8: // Display Booking History
                        parkingSystem.displayBookings();
                        break;

                    case 9: // Exit
                        System.out.println("Exiting Smart Parking Navigation System.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

class User {
    private String userId;
    private String password;
    private String name;

    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public boolean authenticate(String password) { return this.password.equals(password); }
}

class ParkingSlot {
    private int slotId;
    private boolean isOccupied;
    private String vehicleId;
    private LocalDateTime entryTime;

    public ParkingSlot(int slotId) {
        this.slotId = slotId;
        this.isOccupied = false;
        this.vehicleId = null;
        this.entryTime = null;
    }

    public int getSlotId() { return slotId; }
    public boolean isOccupied() { return isOccupied; }
    public String getVehicleId() { return vehicleId; }
    public LocalDateTime getEntryTime() { return entryTime; }

    public void parkVehicle(String vehicleId) {
        this.isOccupied = true;
        this.vehicleId = vehicleId;
        this.entryTime = LocalDateTime.now();
    }

    public void removeVehicle() {
        this.isOccupied = false;
        this.vehicleId = null;
        this.entryTime = null;
    }

    @Override
    public String toString() {
        return "Slot " + slotId + ": " + (isOccupied ? "Occupied (Vehicle: " + vehicleId + ")" : "Free");
    }
}

class Booking {
    private String bookingId;
    private String userId;
    private String vehicleId;
    private int slotId;
    private LocalDateTime bookingTime;
    private double paymentAmount;

    public Booking(String bookingId, String userId, String vehicleId, int slotId, double paymentAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.bookingTime = LocalDateTime.now();
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", User: " + userId + ", Vehicle: " + vehicleId +
               ", Slot: " + slotId + ", Time: " + bookingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
               ", Paid: $" + paymentAmount;
    }
}

class SmartParkingSystem {
    private ArrayList<ParkingSlot> slots;
    private HashMap<String, ParkingSlot> vehicleToSlotMap;
    private HashMap<String, User> users;
    private ArrayList<Booking> bookings;
    private int totalSlots;
    private int availableSlots;
    private Random random;

    public SmartParkingSystem(int totalSlots) {
        this.totalSlots = totalSlots;
        this.availableSlots = totalSlots;
        this.slots = new ArrayList<>();
        this.vehicleToSlotMap = new HashMap<>();
        this.users = new HashMap<>();
        this.bookings = new ArrayList<>();
        this.random = new Random();

        // Initialize parking slots
        for (int i = 1; i <= totalSlots; i++) {
            slots.add(new ParkingSlot(i));
        }
    }

    // User Authentication Module
    public boolean registerUser(String userId, String password, String name) {
        if (users.containsKey(userId)) {
            System.out.println("User ID already exists!");
            return false;
        }
        users.put(userId, new User(userId, password, name));
        System.out.println("User " + name + " registered successfully.");
        return true;
    }

    public User loginUser(String userId, String password) {
        User user = users.get(userId);
        if (user != null && user.authenticate(password)) {
            System.out.println("Login successful for " + user.getName());
            return user;
        }
        System.out.println("Invalid user ID or password.");
        return null;
    }

    // Slot Detection Module (Simulated)
    public void simulateSlotDetection() {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && random.nextDouble() < 0.2) { // 20% chance to simulate occupancy
                slot.parkVehicle("SIM-" + slot.getSlotId());
                vehicleToSlotMap.put("SIM-" + slot.getSlotId(), slot);
                availableSlots--;
            }
        }
    }

    // Navigation Module
    public int findNearestSlot() {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                return slot.getSlotId();
            }
        }
        return -1; // No slots available
    }

    // Reservation & Booking Module
    public synchronized boolean bookSlot(String userId, String vehicleId, int slotId, double payment) {
        if (slotId < 1 || slotId > totalSlots) {
            throw new IllegalArgumentException("Invalid slot ID.");
        }
        ParkingSlot slot = slots.get(slotId - 1);
        if (slot.isOccupied()) {
            throw new IllegalStateException("Slot " + slotId + " is already occupied.");
        }
        if (vehicleToSlotMap.containsKey(vehicleId)) {
            throw new IllegalStateException("Vehicle " + vehicleId + " is already parked.");
        }

        slot.parkVehicle(vehicleId);
        vehicleToSlotMap.put(vehicleId, slot);
        availableSlots--;
        String bookingId = "B" + (bookings.size() + 1);
        bookings.add(new Booking(bookingId, userId, vehicleId, slotId, payment));
        System.out.println("Vehicle " + vehicleId + " booked in slot " + slotId + " by " + userId);
        return true;
    }

    // Payment Module
    public double calculatePayment(LocalDateTime entryTime) {
        long hours = java.time.Duration.between(entryTime, LocalDateTime.now()).toHours();
        return hours * 2.0; // $2 per hour
    }

    // Exit and Payment
    public synchronized void exitParking(String vehicleId) {
        ParkingSlot slot = vehicleToSlotMap.get(vehicleId);
        if (slot == null) {
            throw new IllegalStateException("Vehicle " + vehicleId + " not found.");
        }
        double payment = calculatePayment(slot.getEntryTime());
        slot.removeVehicle();
        vehicleToSlotMap.remove(vehicleId);
        availableSlots++;
        System.out.println("Vehicle " + vehicleId + " exited. Payment: $" + payment);
    }

    // Database Management (In-memory simulation)
    public void displayBookings() {
        System.out.println("\nBooking History:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    // Display Parking Status
    public void displayStatus() {
        System.out.println("\nParking Status (Available Slots: " + availableSlots + "/" + totalSlots + ")");
        for (ParkingSlot slot : slots) {
            System.out.println(slot);
        }
    }
}