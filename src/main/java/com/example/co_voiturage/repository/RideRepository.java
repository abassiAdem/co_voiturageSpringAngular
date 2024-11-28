package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {

}