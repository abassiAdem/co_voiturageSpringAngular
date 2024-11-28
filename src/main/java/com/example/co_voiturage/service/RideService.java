package com.example.co_voiturage.service;


import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }
}
