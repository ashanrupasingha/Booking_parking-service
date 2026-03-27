package com.spms.parkingservice.service;

import com.spms.parkingservice.model.Booking;
import com.spms.parkingservice.model.ParkingSlot;
import com.spms.parkingservice.repository.BookingRepository;
import com.spms.parkingservice.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {
    private final ParkingSlotRepository slotRepository;
    private final BookingRepository bookingRepository;

    public ParkingService(ParkingSlotRepository slotRepository, BookingRepository bookingRepository) {
        this.slotRepository = slotRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    public List<ParkingSlot> getAvailableSlots() {
        return slotRepository.findByStatus("AVAILABLE");
    }

    public ParkingSlot addSlot(ParkingSlot slot) {
        slot.setStatus("AVAILABLE");
        return slotRepository.save(slot);
    }

    // Seeds 10 sample parking slots for testing
    public void seedSampleSlots() {
        String[] floors = {"G", "G", "G", "G", "1", "1", "1", "2", "2", "2"};
        for (int i = 1; i <= 10; i++) {
            ParkingSlot slot = new ParkingSlot();
            slot.setSlotNumber("S-" + String.format("%03d", i));
            slot.setFloor(floors[i - 1]);
            slot.setStatus("AVAILABLE");
            slotRepository.save(slot);
        }
    }

    public Booking bookSlot(Long userId, String slotId) {
        ParkingSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found: " + slotId));

        if (!"AVAILABLE".equals(slot.getStatus())) {
            throw new RuntimeException("Slot is not available");
        }

        slot.setStatus("OCCUPIED");
        slotRepository.save(slot);

        Booking booking = new Booking(null, userId, slotId, LocalDateTime.now(), "ACTIVE");
        return bookingRepository.save(booking);
    }

    public void releaseSlot(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));

        booking.setStatus("COMPLETED");
        bookingRepository.save(booking);

        slotRepository.findById(booking.getSlotId()).ifPresent(slot -> {
            slot.setStatus("AVAILABLE");
            slotRepository.save(slot);
        });
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
