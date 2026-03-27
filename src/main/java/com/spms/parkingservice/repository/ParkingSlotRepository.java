package com.spms.parkingservice.repository;

import com.spms.parkingservice.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String> {
    List<ParkingSlot> findByStatus(String status);
}
