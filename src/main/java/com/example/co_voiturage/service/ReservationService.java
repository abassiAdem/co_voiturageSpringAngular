package com.example.co_voiturage.service;


import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RideService rideService;

 /*   public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }*/

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        rideService.decreaseAvailableSeats(reservation.getRideid(), reservation.getNbr_places());
    }

}
