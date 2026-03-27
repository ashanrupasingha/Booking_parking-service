package com.spms.parkingservice.repository;

import com.spms.parkingservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByUserIdAndStatus(Long userId, String status);
}
