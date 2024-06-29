package com.api.Booking.Proxy;

 import com.api.Booking.Dto.FlightResponse;
 import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("flight-service")
public interface FlightServiceFeignClient {

    @GetMapping("/flights/{id}")
    FlightResponse getFlightById(@PathVariable Long id);

    @PutMapping("/flights/{id}/seats")
    void updateAvailableSeats(@PathVariable("id") Long id, @RequestParam("availableSeats") int availableSeats);
}