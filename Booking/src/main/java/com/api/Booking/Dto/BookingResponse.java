package com.api.Booking.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingResponse {
    private Long id;
    private Long userId;
    private Long flightId;
    private LocalDateTime bookingTime;
    private String status;
    private int numberOfSeats;
    private double TotalPrice;

}
