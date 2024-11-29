package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
