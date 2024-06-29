package com.api.Booking.Kafka;

import java.time.LocalDateTime;

public record BookingConfirmation(Long id
                              , String email
                              , Long flightId
                              , LocalDateTime departureTime
                              , int numberOfSeats
                              , double TotalPrice) {
}
