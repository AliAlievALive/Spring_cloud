package com.almir.reservationservices;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "ROOM_ID", nullable = false)
    private Long roomId;
    @Column(name = "GUEST_ID", nullable = false)
    private Long guestId;
    @Column(name = "RES_DATE", nullable = false)
    private Date date;
}
