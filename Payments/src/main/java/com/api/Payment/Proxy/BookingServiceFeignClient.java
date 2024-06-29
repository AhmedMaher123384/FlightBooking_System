package com.api.Payment.Proxy;

import com.api.Payment.Dto.BookingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("booking-service")
public interface BookingServiceFeignClient {
    @GetMapping("/bookings/{id}")
    BookingResponse getBookingById(@PathVariable Long id);
}