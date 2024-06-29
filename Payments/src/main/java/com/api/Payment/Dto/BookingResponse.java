package com.api.Payment.Dto;

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
    private double TotalPrice;
    private String status;
    private int numberOfSeats;


}

