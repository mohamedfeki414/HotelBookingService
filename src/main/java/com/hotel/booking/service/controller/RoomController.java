package com.hotel.booking.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.service.exceptions.NoSuchElementFoundException;
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
    
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
        .orElseThrow(()->new NoSuchElementFoundException("id not found")); 
    }


    
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        
        System.out.println("Requête reçue : " + room);
        return roomRepository.save(room);
    }
   @PutMapping ("/{id}")
     public Room updateRoom(@RequestBody Room room , @PathVariable Long id) {
	   return roomRepository.findById(id)
	       .map(existingRoom -> {
          
           existingRoom.setRoomNumber(room.getRoomNumber());
           existingRoom.setType(room.getType());
           existingRoom.setPricePerNight(room.getPricePerNight());
           existingRoom.setIsAvailable(room.getIsAvailable());
           
           return roomRepository.save(existingRoom);
       })
	   .orElseThrow(() -> new NoSuchElementFoundException("Room not found with id " + id));
   }
   @DeleteMapping("/{id}")
   public void deleteRoom(@PathVariable Long id) {
       roomRepository.deleteById(id);
   }
   
}
