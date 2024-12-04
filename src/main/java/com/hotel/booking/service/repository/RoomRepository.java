package com.hotel.booking.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.service.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

}
