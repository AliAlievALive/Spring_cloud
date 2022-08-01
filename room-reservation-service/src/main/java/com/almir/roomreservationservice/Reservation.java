package com.almir.roomreservationservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Reservation {
    private Long id;
    private Long roomId;
    private Long guestId;
    private Date date;
}
