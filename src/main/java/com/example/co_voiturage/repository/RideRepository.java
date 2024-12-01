package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
   @Query("select r from Ride r where r.dateDepart = :dateDepart and r.destination = :destination and r.depart = :depart and r.nbrPlaces >= :nbrPlaces")
   List<Ride> searchRideByDateDepartAndDestinationAndDepartAndNbrPlaces(String dateDepart, String destination, String depart, int nbrPlaces);

   @Query("select r from Ride r where r.dateDepart >= :dateDepart")
   List<Ride> getAllRideByDateDepartAfter(String dateDepart);

   List<Ride> findByUserId(Long userId);


}