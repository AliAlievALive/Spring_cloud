package com.almir.roomreservationservice;

import lombok.Data;

@Data
public class Room {
    private long id;
    private String name;
    private String roomNumber;
    private String bedInfo;
}
