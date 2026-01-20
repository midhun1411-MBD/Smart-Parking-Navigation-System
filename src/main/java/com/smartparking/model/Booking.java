package com.smartparking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String bookingId;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private String vehicleId;
    
    @Column(nullable = false)
    private Integer slotId;
    
    @Column(nullable = false)
    private LocalDateTime bookingTime;
    
    @Column(nullable = false)
    private Double paymentAmount;
    
    public Booking() {
    }
    
    public Booking(String bookingId, String userId, String vehicleId, Integer slotId, Double paymentAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.bookingTime = LocalDateTime.now();
        this.paymentAmount = paymentAmount;
    }
    
    public Booking(Long id, String bookingId, String userId, String vehicleId, Integer slotId, LocalDateTime bookingTime, Double paymentAmount) {
        this.id = id;
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.bookingTime = bookingTime;
        this.paymentAmount = paymentAmount;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public Integer getSlotId() {
        return slotId;
    }
    
    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }
    
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    
    public Double getPaymentAmount() {
        return paymentAmount;
    }
    
    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
