package com.almir.reservationservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository repository;

    @Autowired
    public ReservationController(ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Reservation> getReservationsByDate(@RequestParam(name = "date", required = false) Date date) {
        if (date != null) {
            return repository.findAllByDate(date);
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id) {
        return repository.findById(id).orElseThrow();
    }
}
