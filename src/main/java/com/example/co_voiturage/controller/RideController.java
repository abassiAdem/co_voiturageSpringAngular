package com.example.co_voiturage.controller;


import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.model.User;
import com.example.co_voiturage.service.RideService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RideController {
    @Autowired
    private RideService rideService;

    @GetMapping("/rides")
    public String viewRidesPage(HttpSession session, Model model) {
        String userRole = (String) session.getAttribute("userRole");//aAutomatically retrieved
        model.addAttribute("listRides", rideService.getAllRides());
        model.addAttribute("userRole",userRole);
        return "rides";
    }


    @GetMapping("/showNewRideForm")
    public String showNewRideForm(Model model) {
        Ride ride = new Ride();
        model.addAttribute("ride", ride);
        return "new_ride";
    }

    @PostMapping("/saveRide")
    public String saveRide(@ModelAttribute("ride") Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/rides";
    }
}