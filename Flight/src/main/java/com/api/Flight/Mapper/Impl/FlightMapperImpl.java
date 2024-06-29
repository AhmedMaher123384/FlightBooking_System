package com.api.Flight.Mapper.Impl;


import com.api.Flight.Dto.FlightRequest;
import com.api.Flight.Dto.FlightResponse;
import com.api.Flight.Mapper.FlightMapper;
import com.api.Flight.Model.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FlightMapperImpl implements FlightMapper {

    public Flight toEntity(FlightRequest flightRequest) {
        return Flight.builder()
                .flightNumber(flightRequest.getFlightNumber())
                .origin(flightRequest.getOrigin())
                .destination(flightRequest.getDestination())
                .departureTime(flightRequest.getDepartureTime())
                .arrivalTime(flightRequest.getArrivalTime())
                .availableSeats(flightRequest.getAvailableSeats())
                .price(flightRequest.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .build();
    }

    public FlightResponse toDto(Flight flight) {
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .origin(flight.getOrigin())
                .destination(flight.getDestination())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .availableSeats(flight.getAvailableSeats())
                .price(flight.getPrice())
                .active(flight.isActive())
                .build();
    }
}