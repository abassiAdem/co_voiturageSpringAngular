package com.example.co_voiturage.service;

import com.example.co_voiturage.model.Reviews;
import com.example.co_voiturage.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    public void saveReview(Reviews reviews) {
        reviewsRepository.save(reviews);
    }

    public List<Reviews> getAllReviews(Long id) {
        return reviewsRepository.findAllByConducteurid(id);
    }

}
