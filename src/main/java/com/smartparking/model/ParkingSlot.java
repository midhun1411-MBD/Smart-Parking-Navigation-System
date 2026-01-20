package com.smartparking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private Integer slotId;
    
    @Column(nullable = false)
    private Boolean isOccupied = false;
    
    private String vehicleId;
    
    private LocalDateTime entryTime;
    
    public ParkingSlot() {
    }
    
    public ParkingSlot(Integer slotId) {
        this.slotId = slotId;
        this.isOccupied = false;
    }
    
    public ParkingSlot(Long id, Integer slotId, Boolean isOccupied, String vehicleId, LocalDateTime entryTime) {
        this.id = id;
        this.slotId = slotId;
        this.isOccupied = isOccupied;
        this.vehicleId = vehicleId;
        this.entryTime = entryTime;
    }
    
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
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getSlotId() {
        return slotId;
    }
    
    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }
    
    public Boolean getIsOccupied() {
        return isOccupied;
    }
    
    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    
    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}
