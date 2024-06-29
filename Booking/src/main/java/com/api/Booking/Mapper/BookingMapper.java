package com.api.Booking.Mapper;


import com.api.Booking.Dto.BookingRequest;
import com.api.Booking.Dto.BookingResponse;
import com.api.Booking.Model.Booking;

public interface BookingMapper {
    Booking toEntity(BookingRequest bookingRequest, double totalPrice);
    BookingResponse toDto(Booking booking);
}
