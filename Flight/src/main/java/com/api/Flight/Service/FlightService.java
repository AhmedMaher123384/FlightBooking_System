package com.api.Flight.Service;



import com.api.Flight.Dto.FlightRequest;
import com.api.Flight.Dto.FlightResponse;

import java.util.List;

public interface FlightService {
    FlightResponse getFlightById(Long id);
    List<FlightResponse> getFlightsByRoute(String origin, String destination);
    void updateAvailableSeats(Long flightId, int availableSeats);
    FlightResponse createFlight(FlightRequest flightRequest);
}