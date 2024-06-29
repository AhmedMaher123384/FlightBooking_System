package com.api.Flight.Repository;

import com.api.Flight.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<List<Flight>> findByOriginAndDestination(String origin, String destination);
}