package com.example.co_voiturage.controller;

import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.model.Reviews;
import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.service.ReviewsService;
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
import java.util.List;

@Controller
public class RideController {
    @Autowired
    private RideService rideService;

    @Autowired
    private ReviewsService reviewsService;

    @GetMapping("/rideDetails")
    public String showRideDetails(@RequestParam("rideId") Long rideId, Model model,HttpSession session) {
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userName",userName);
        Ride ride = rideService.findById(rideId);
        if (ride == null) {
            return "redirect:/rides";
        }
        List<Reviews> reviews = reviewsService.getAllReviews(ride.getUserId());
        model.addAttribute("ride", ride);
        model.addAttribute("reviews", reviews);
        model.addAttribute("rev", new Reviews());
        return "rideDetails";
    }

    @GetMapping("/rides")
    public String viewRidesPage(HttpSession session, Model model) {
        String currentDate = LocalDate.now().toString();
        String userRole = (String) session.getAttribute("userRole");
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("listRides", rideService.getAllRideByDateDepartAfter(currentDate));
        model.addAttribute("userRole",userRole);
        model.addAttribute("userId",userId);
        model.addAttribute("reservation",new Reservation());

        return "rides";
    }

    @GetMapping("/showNewRideForm")
    public String showNewRideForm(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userName",userName);
        model.addAttribute("userId",userId);
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
                              @RequestParam String date_depart, @RequestParam int nbr_places,Model model) {

        model.addAttribute("today", LocalDate.now());
        model.addAttribute("listRides", rideService.searchRide(date_depart, destination, depart, nbr_places));
        model.addAttribute("reservation",new Reservation());
        return "rides";
    }




   @GetMapping("/MonRides")
    public String myRides(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("myRides", rideService.findByUserId(userId));
        return "MonRides";
    }



}