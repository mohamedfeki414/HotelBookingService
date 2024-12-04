package com.hotel.booking.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.service.model.Room;
import com.hotel.booking.service.repository.RoomRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/rooms")
@Tag(name ="Room", description = "the product API")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;


    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        
        System.out.println("Requête reçue : " + room);
        return roomRepository.save(room);
    }
   @PutMapping
     void updateRoom(@RequestBody Room room) {
	   roomRepository.save(room);
   }
   
}
