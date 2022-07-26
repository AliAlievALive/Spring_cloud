package com.almir.roomreservationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@FeignClient("reservationsevices")
public interface ReservationClient {
    @GetMapping("/reservations")
    List<Reservation> getAllReservations(@RequestParam(name = "date", required = false) Date date);

    @GetMapping("reservations/{id}")
    Reservation getReservation(@PathVariable("id") Long id);
}
