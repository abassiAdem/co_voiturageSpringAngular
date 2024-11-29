package com.example.co_voiturage.controller;


import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.model.User;
import com.example.co_voiturage.repository.RideRepository;
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
public class RideController {
    @Autowired
    private RideService rideService;


    @GetMapping("/rides")
    public String viewRidesPage(HttpSession session, Model model) {

        String userRole = (String) session.getAttribute("userRole");//aAutomatically retrieved
        Long userId = (Long) session.getAttribute("userId");//aAutomatically retrieved
        model.addAttribute("listRides", rideService.getAllRides());
        model.addAttribute("userRole",userRole);
        model.addAttribute("userId",userId);
        Reservation reservation = new Reservation();
        model.addAttribute("reservation",reservation);
        return "rides";
    }

    @GetMapping("/showNewRideForm")
    public String showNewRideForm(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");//aAutomatically retrieved
        model.addAttribute("userName",userName);
        Ride ride = new Ride();
        model.addAttribute("ride", ride);
        return "new_ride";
    }

    @PostMapping("/saveRide")
    public String saveRide(@ModelAttribute("ride") Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/rides";
    }

    @PostMapping("/UserHomePage")
    public String filterRides(@RequestParam String depart, @RequestParam String destination,
                              @RequestParam String date_depart, @RequestParam int nbr_places, Model model) {

        model.addAttribute("listRides", rideService.searchRide(date_depart, destination, depart, nbr_places));
        return "rides";
    }










}