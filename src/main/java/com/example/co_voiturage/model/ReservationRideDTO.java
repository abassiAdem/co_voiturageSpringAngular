package com.example.co_voiturage.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ReservationRideDTO {
    private Reservation reservation;
    private Ride ride;

}