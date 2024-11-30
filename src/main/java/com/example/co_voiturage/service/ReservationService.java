package com.example.co_voiturage.service;


import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.model.Ride;
import com.example.co_voiturage.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RideService rideService;

    public List<Long> findReservationIdByUserId(Long id) {
        return reservationRepository.findReservationIdByUserId(id);
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        rideService.decreaseAvailableSeats(reservation.getRideid(), reservation.getNbr_places());
    }

    public void backAvailableSeats(Reservation reservation) {

        rideService.backAvailableSeats(reservation.getRideid(), reservation.getNbr_places());
        reservationRepository.delete(reservation);
    }

}
