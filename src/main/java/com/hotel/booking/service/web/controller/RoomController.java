package com.hotel.booking.service.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.service.model.Room;
import com.hotel.booking.service.web.dao.RoomDao;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	@Autowired
    private RoomDao roomdao;
	
	@GetMapping
    public List<Room> getAllRooms() {
        return roomdao.findAll();
    }
	
	@PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomdao.save(room);
    }

}
