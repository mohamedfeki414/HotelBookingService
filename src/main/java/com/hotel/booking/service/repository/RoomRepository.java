package com.hotel.booking.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.service.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

	List<Room> findByOrderByPricePerNightDesc();

	List<Room> findByType(String type);

	List<Room> findByIsAvailable(Boolean is_available);
	 
	boolean existsByRoomNumber(Integer roomNumber);

}
