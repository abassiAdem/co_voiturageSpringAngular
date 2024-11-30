package com.example.co_voiturage.controller;


import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.model.User;
import com.example.co_voiturage.service.ReservationService;
import com.example.co_voiturage.service.RideService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RideService rideService;

    @PostMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/rides";
    }


    @GetMapping("/historie")
    public String getAllReservations(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        List<Long> ids = reservationService.findReservationIdByUserId(userId);
        model.addAttribute("listRides", rideService.findAllById(ids)  );
        return "historie";
    }




}
