package com.smartparking.repository;

import com.smartparking.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    Optional<ParkingSlot> findBySlotId(Integer slotId);
    Optional<ParkingSlot> findByVehicleId(String vehicleId);
    List<ParkingSlot> findByIsOccupied(Boolean isOccupied);
    long countByIsOccupied(Boolean isOccupied);
}
