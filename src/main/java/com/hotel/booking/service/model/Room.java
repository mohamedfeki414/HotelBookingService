package com.hotel.booking.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String type; // Single, Double, Suite
    private Double pricePerNight;
    private Boolean isAvailable;
    
    public Room(Long id, String roomNumber, String type, Double pricePerNight, Boolean isAvailable) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.type = type;
		this.pricePerNight = pricePerNight;
		this.isAvailable = isAvailable;
	}
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(Double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
