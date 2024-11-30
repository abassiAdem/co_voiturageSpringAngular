package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> searchRideByDateDepartAndDestinationAndDepartAndNbrPlaces(String dateDepart, String destination, String depart, int nbrPlaces);

}