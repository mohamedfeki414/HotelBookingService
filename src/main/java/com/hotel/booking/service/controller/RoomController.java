package com.hotel.booking.service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

   

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    @Operation(summary = "Obtenir la liste des chambres",description = "Récupère toutes les chambres disponibles.")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une chambre par ID", description = "Récupère les détails d'une chambre en utilisant son identifiant unique.")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("id not found"));
    }

    @PostMapping
    @Operation(summary = "Créer une chambre", description = "Crée une nouvelle chambre avec les informations fournies.")
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
        if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
            throw new InvalidInputException("Room number must be unique. Room with this number already exists.");
        }

        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une chambre par ID",description = "Met à jour les détails d'une chambre en utilisant son identifiant unique.")
    public Room updateRoom(@RequestBody Room room, @PathVariable Long id) {
    	 if (room.getPricePerNight() <= 0) {
             throw new InvalidInputException("The price per night must be greater than 0.");
         }

         if (!VALID_ROOM_TYPES.contains(room.getType())) {
             throw new InvalidInputException("The room type must be one of the following: Single, Double, Suite.");
         }

         if (room.getRoomNumber() < 1 || room.getRoomNumber() > 99) {
             throw new InvalidInputException("The room number must be between 1 and 99.");
         }
        
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
    @Operation(summary = "Supprimer une chambre par ID",description = "Supprime une chambre en utilisant son identifiant unique.")
    public void deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
    }
    @GetMapping("/orderBY/prix")
    @Operation(summary = "Lister les chambres par prix décroissant",description = "Retourne une liste de chambres triées par prix décroissant.")
    public List<Room>findByOrderByPricePerNightDesc(){
    	return roomRepository.findByOrderByPricePerNightDesc();
    }
    @GetMapping("/type/{type}")
    @Operation(summary = "Trouver les chambres par type",description = "Retourne une liste de chambres en fonction de leur type (par exemple, single, double, suite).")
    public List<Room>findByType(@PathVariable String type){
    	return roomRepository.findByType(type);
    }
    @GetMapping("/dispo/dis/{is_available}")
    @Operation(summary = "Trouver les chambres disponibles",description = "Retourne une liste de chambres disponibles ou non disponibles en fonction du statut spécifié.")
    public List<Room>findByIsAvailable(@PathVariable Boolean is_available){
    	return roomRepository.findByIsAvailable(is_available);
    }
    @GetMapping("/r/r/r/r/{type}")
    @Operation(summary = "Trouver le nombre des chambre chambres disponibles non type ",description = "Retourne le nombre du chambre non single")
    public long getNonSingleRoomCount(String type) {
        return roomRepository.countByTypeNot("type");
    }
}
    
   

