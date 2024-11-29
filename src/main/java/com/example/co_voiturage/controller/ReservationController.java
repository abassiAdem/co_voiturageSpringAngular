package com.example.co_voiturage.controller;


import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.service.ReservationService;
import com.example.co_voiturage.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    @PostMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "rides";
    }



}
