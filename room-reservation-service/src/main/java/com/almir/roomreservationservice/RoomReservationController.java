package com.almir.roomreservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationController {
    private final RoomClient roomClient;
    private final ReservationClient reservationClient;
    private final GuestClient guestClient;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public RoomReservationController(RoomClient roomClient, ReservationClient reservationClient, GuestClient guestClient) {
        this.roomClient = roomClient;
        this.reservationClient = reservationClient;
        this.guestClient = guestClient;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations(@RequestParam(name = "date", required = false) String dateString) {
        Date date = createDateFromDateString(dateString);
        List<Room> rooms = roomClient.getAllRooms();
        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomId(room.getId());
            roomReservations.put(room.getId(), roomReservation);
        });
        List<Reservation> reservations = reservationClient.getAllReservations(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = guestClient.getGuest(reservation.getGuestId());
            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });
        return new ArrayList<>(roomReservations.values());
    }

    private Date createDateFromDateString(String dateString) {
        Date date;
        if (dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);
            } catch (ParseException parseException) {
                date = new Date();
            }
        } else {
            date = new Date();
        }
        return date;
    }

}
