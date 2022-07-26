package com.almir.roomservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository repository;

    @Autowired
    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Room> getAllRooms() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        return repository.findById(id).orElseThrow();
    }
}
