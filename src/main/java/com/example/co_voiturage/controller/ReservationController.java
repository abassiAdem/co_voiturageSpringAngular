package com.example.co_voiturage.controller;


import com.example.co_voiturage.model.*;
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

import java.time.LocalDate;
import java.util.ArrayList;
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

        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName",userName);
        model.addAttribute("rev", new Reviews());
        Long userId = (Long) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("userRole");
        model.addAttribute("userRole",userRole);
        model.addAttribute("userId",userId);
        LocalDate currentDate = LocalDate.now();
        List<ReservationRideDTO> reservationRides = new ArrayList<>();
        List<ReservationRideDTO> pasedreservationRides = new ArrayList<>();
        List<Reservation> reservations = reservationService.findAllByUserid(userId);
        for (Reservation reservation : reservations) {
            Ride ride = rideService.findById(reservation.getRideid());
            if(LocalDate.parse(ride.getDateDepart()).isBefore(currentDate)){
                ReservationRideDTO pdto = new ReservationRideDTO();
                pdto.setReservation(reservation);
                pdto.setRide(ride);
                pasedreservationRides.add(pdto);
            }
            else{
            ReservationRideDTO dto = new ReservationRideDTO();
            dto.setReservation(reservation);
            dto.setRide(ride);

            reservationRides.add(dto);}
        }
        model.addAttribute("reservationRides", reservationRides);
        model.addAttribute("pasedreservationRides", pasedreservationRides);
        return "historie";
    }

    @PostMapping("/deleteReservation")
    public String deleteReservation(@ModelAttribute("reservation") Reservation reservation) {

        reservationService.deleteReservation(reservation);
        return "redirect:/historie";
    }





}
