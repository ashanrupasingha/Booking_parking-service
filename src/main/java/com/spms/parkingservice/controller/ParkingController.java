package com.spms.parkingservice.controller;

import com.spms.parkingservice.model.Booking;
import com.spms.parkingservice.model.ParkingSlot;
import com.spms.parkingservice.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/slots")
    public List<ParkingSlot> getAllSlots() {
        return parkingService.getAllSlots();
    }

    @GetMapping("/slots/available")
    public List<ParkingSlot> getAvailableSlots() {
        return parkingService.getAvailableSlots();
    }

    @PostMapping("/slots")
    public ResponseEntity<ParkingSlot> addSlot(@RequestBody ParkingSlot slot) {
        return ResponseEntity.ok(parkingService.addSlot(slot));
    }

    // Seed 10 sample slots — returns JSON
    @PostMapping("/slots/seed")
    public ResponseEntity<Map<String, Object>> seedSlots() {
        parkingService.seedSampleSlots();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "10 sample parking slots created successfully"
        ));
    }

    @PostMapping("/book/{userId}/{slotId}")
    public ResponseEntity<Booking> bookSlot(
            @PathVariable Long userId,
            @PathVariable String slotId) {
        return ResponseEntity.ok(parkingService.bookSlot(userId, slotId));
    }

    // Release slot — returns JSON
    @PostMapping("/release/{bookingId}")
    public ResponseEntity<Map<String, Object>> releaseSlot(@PathVariable String bookingId) {
        parkingService.releaseSlot(bookingId);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Slot released successfully"
        ));
    }

    @GetMapping("/bookings/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return parkingService.getUserBookings(userId);
    }
}
