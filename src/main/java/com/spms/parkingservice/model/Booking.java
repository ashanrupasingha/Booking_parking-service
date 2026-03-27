package com.spms.parkingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private Long userId;
    private String slotId;
    private LocalDateTime bookingTime;
    private String status;

    public Booking() {}
    public Booking(String id, Long userId, String slotId, LocalDateTime bookingTime, String status) {
        this.id = id;
        this.userId = userId;
        this.slotId = slotId;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getSlotId() { return slotId; }
    public void setSlotId(String slotId) { this.slotId = slotId; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
