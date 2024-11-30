package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.Reservation;
import com.example.co_voiturage.model.Ride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserid(Long userid);
    @Query("SELECT rideid FROM Reservation  WHERE userid = :userId")
    List<Long> findReservationIdByUserId(Long userId);

}
