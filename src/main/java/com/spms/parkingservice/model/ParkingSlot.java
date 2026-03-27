package com.spms.parkingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parking_slots")
public class ParkingSlot {
    @Id
    private String id;
    private String slotNumber;
    private String floor;
    private String status; // AVAILABLE, OCCUPIED

    public ParkingSlot() {}
    public ParkingSlot(String id, String slotNumber, String floor, String status) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.floor = floor;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
