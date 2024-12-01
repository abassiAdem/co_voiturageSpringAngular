package com.example.co_voiturage.service;


import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;


    public List<Ride> findByUserId(Long userId) {
        return rideRepository.findByUserId(userId);
    }

    public List<Ride> getAllRideByDateDepartAfter(String dateDepartAfter) {
        return rideRepository.getAllRideByDateDepartAfter(dateDepartAfter);
    }


    public Ride findById(Long rideid) {
        Optional<Ride> ride =rideRepository.findById(rideid);
        return ride.orElse(null);
    }

    public List<Ride> searchRide(String dateDepart, String destination, String depart, int nbrPlaces ) {
        return  rideRepository.searchRideByDateDepartAndDestinationAndDepartAndNbrPlaces(dateDepart,destination,depart,nbrPlaces);
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    public void decreaseAvailableSeats(Long rideId, int seats) {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);

        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            int updatedSeats = ride.getNbrPlaces() - seats;

            if (updatedSeats >= 0) {
                ride.setNbrPlaces(updatedSeats);
                rideRepository.save(ride);
            } else {
                System.out.println("Pas assez de places disponibles.");
            }
        } else {
            System.out.println("Trajet introuvable.");
        }
    }

    public void increaseAvailableSeats(Long rideId, int seats) {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);

        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            int updatedSeats = ride.getNbrPlaces() + seats;

            if (updatedSeats >= 0) {
                ride.setNbrPlaces(updatedSeats);
                rideRepository.save(ride);
            } else {
                System.out.println("Pas assez de places disponibles.");
            }
        } else {
            System.out.println("Trajet introuvable.");
        }
    }



}
