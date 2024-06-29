package com.api.Booking.Service;

import com.api.Booking.Dto.BookingRequest;
import com.api.Booking.Dto.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
    BookingResponse getBookingById(Long id);
}
