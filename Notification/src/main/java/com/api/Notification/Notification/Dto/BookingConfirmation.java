package com.api.Notification.Notification.Dto;

import java.time.LocalDateTime;

public record BookingConfirmation(Long id
                              , String email
                              , Long flightId
                              , LocalDateTime departureTime
                              , int numberOfSeats
                              , double TotalPrice) {
}
