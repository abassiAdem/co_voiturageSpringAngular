package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findAllByConducteurid(Long conducteurid);
}
