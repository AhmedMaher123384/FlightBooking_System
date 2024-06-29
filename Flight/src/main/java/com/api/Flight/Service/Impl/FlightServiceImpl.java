package com.api.Flight.Service.Impl;


import com.api.Flight.Dto.FlightRequest;
import com.api.Flight.Dto.FlightResponse;
import com.api.Flight.Exception.FlightNotFoundException;
import com.api.Flight.Exception.RouteNotFoundException;
import com.api.Flight.Mapper.FlightMapper;
import com.api.Flight.Model.Flight;
import com.api.Flight.Repository.FlightRepository;
import com.api.Flight.Service.FlightService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public FlightResponse getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight not found"));
        return flightMapper.toDto(flight);
    }

    @Override
    @CircuitBreaker(name = "flightServiceCircuitBreaker", fallbackMethod = "fallbackGetFlightsByRoute")
    public List<FlightResponse> getFlightsByRoute(String origin, String destination) {
        List<Flight> flights = flightRepository.findByOriginAndDestination(origin, destination).orElseThrow(()-> new RouteNotFoundException("Route not found"));
        return flights.stream().map(flightMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name = "flightServiceCircuitBreaker", fallbackMethod = "fallbackUpdateSeats")
    public void updateAvailableSeats(Long flightId, int availableSeats) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight not found"));
        flight.setAvailableSeats(availableSeats);
        flight.setUpdatedAt(LocalDateTime.now());
        flightRepository.save(flight);
    }

    @Override
    @CircuitBreaker(name = "flightServiceCircuitBreaker", fallbackMethod = "fallbackCreateFlight")
    public FlightResponse createFlight(FlightRequest flightRequest) {
        Flight flight = flightMapper.toEntity(flightRequest);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    public List<FlightResponse> fallbackGetFlightsByRoute(String origin, String destination, Throwable throwable) {
        return Collections.singletonList(FlightResponse.builder()
                .flightNumber("Fallback Flight")
                .origin("N/A")
                .destination("N/A")
                .departureTime(LocalDateTime.now())
                .arrivalTime(LocalDateTime.now())
                .availableSeats(0)
                .price(0)
                .active(false)
                .build());
    }

    public void fallbackUpdateSeats(Long flightId, int availableSeats, Throwable throwable) {
        System.out.println("Fallback updating seats for flight id: " + flightId);
    }

    public FlightResponse fallbackCreateFlight(FlightRequest flightRequest, Throwable throwable) {
        return FlightResponse.builder()
                .flightNumber("Fallback Flight")
                .origin("N/A")
                .destination("N/A")
                .departureTime(LocalDateTime.now())
                .arrivalTime(LocalDateTime.now())
                .availableSeats(0)
                .price(0)
                .active(false)
                .build();
    }
}