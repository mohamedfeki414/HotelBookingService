package com.hotel.booking.service.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.service.model.Room;

@Repository
public interface RoomDao extends JpaRepository<Room,Integer> {

}
