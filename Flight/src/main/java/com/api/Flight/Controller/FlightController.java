package com.api.Flight.Controller;


import com.api.Flight.Dto.FlightRequest;
import com.api.Flight.Dto.FlightResponse;
import com.api.Flight.Service.Impl.FlightServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightServiceImpl flightService;

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        FlightResponse flightResponse = flightService.getFlightById(id);
        return new ResponseEntity<>(flightResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getFlightsByRoute(@RequestParam String origin, @RequestParam String destination) {
        List<FlightResponse> flightResponses = flightService.getFlightsByRoute(origin, destination);
        return new ResponseEntity<>(flightResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest) {
        FlightResponse flightResponse = flightService.createFlight(flightRequest);
        return new ResponseEntity<>(flightResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/seats")
    public ResponseEntity<Void> updateAvailableSeats(@PathVariable Long id, @RequestParam int availableSeats) {
        flightService.updateAvailableSeats(id, availableSeats);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
