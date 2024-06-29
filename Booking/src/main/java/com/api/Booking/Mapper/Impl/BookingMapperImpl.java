package com.api.Booking.Mapper.Impl;

import com.api.Booking.Dto.BookingRequest;
import com.api.Booking.Dto.BookingResponse;
import com.api.Booking.Mapper.BookingMapper;
import com.api.Booking.Model.Booking;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class BookingMapperImpl implements BookingMapper {

    public Booking toEntity(BookingRequest bookingRequest, double totalPrice) {
        return Booking.builder()
                .userId(bookingRequest.getUserId())
                .flightId(bookingRequest.getFlightId())
                .bookingTime(LocalDateTime.now())
                .status("CONFIRMED")
                .numberOfSeats(bookingRequest.getNumberOfSeats())
                .TotalPrice(totalPrice)
                .build();
    }

    public BookingResponse toDto(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .flightId(booking.getFlightId())
                .bookingTime(booking.getBookingTime())
                .status(booking.getStatus())
                .numberOfSeats(booking.getNumberOfSeats())
                .TotalPrice(booking.getTotalPrice())
                .build();
    }
}