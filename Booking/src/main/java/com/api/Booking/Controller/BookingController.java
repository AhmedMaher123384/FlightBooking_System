package com.api.Booking.Controller;


import com.api.Booking.Dto.BookingRequest;
import com.api.Booking.Dto.BookingResponse;
import com.api.Booking.Service.Impl.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingServiceImpl.createBooking(bookingRequest);
        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse bookingResponse = bookingServiceImpl.getBookingById(id);
        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }


}