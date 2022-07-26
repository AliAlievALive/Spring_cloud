package com.almir.reservationservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository repository;

    @Autowired
    public ReservationController(ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Reservation> getAllReservations() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id) {
        return repository.findById(id).orElseThrow();
    }
}
