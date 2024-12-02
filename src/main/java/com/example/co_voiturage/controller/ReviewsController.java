package com.example.co_voiturage.controller;

import com.example.co_voiturage.model.Reviews;
import com.example.co_voiturage.model.Ride;
import org.springframework.ui.Model;
import com.example.co_voiturage.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ReviewsController {
    @Autowired
    private ReviewsService reviewsService;

    @PostMapping("/makeReview")
    public String makeReview(@ModelAttribute("rev") Reviews Review) {
        reviewsService.saveReview(Review);
        return "redirect:/historie";
    }


}
