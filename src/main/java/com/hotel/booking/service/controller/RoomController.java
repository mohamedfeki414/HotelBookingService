package com.hotel.booking.service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.hotel.booking.service.exceptions.InvalidInputException;
import com.hotel.booking.service.exceptions.NoSuchElementFoundException;
import com.hotel.booking.service.model.Room;
import com.hotel.booking.service.repository.RoomRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/rooms")
@Tag(name ="Room", description = "the product API")
public class RoomController {

    private static final List<String> VALID_ROOM_TYPES = List.of("Single", "Double", "Suite");

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("id not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new room", description = "This endpoint allows you to create a new room.")
    public Room createRoom(@RequestBody Room room) {
        if (room.getPricePerNight() <= 0) {
            throw new InvalidInputException("The price per night must be greater than 0.");
        }

        if (!VALID_ROOM_TYPES.contains(room.getType())) {
            throw new InvalidInputException("The room type must be one of the following: Single, Double, Suite.");
        }

        if (room.getRoomNumber() < 1 || room.getRoomNumber() > 99) {
            throw new InvalidInputException("The room number must be between 1 and 99.");
        }

        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@RequestBody Room room, @PathVariable Long id) {
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
    @GetMapping("/orderBY/date")
    public List<Room>findByOrderByPricePerNightDesc(){
    	return roomRepository.findByOrderByPricePerNightDesc();
    }
}
