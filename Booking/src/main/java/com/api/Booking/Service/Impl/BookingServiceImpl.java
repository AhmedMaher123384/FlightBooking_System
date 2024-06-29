package com.api.Booking.Service.Impl;


import com.api.Booking.Dto.BookingRequest;
import com.api.Booking.Dto.BookingResponse;
import com.api.Booking.Dto.FlightResponse;
import com.api.Booking.Dto.UserResponse;
import com.api.Booking.Exception.BookingNotFoundException;
import com.api.Booking.Exception.FlightOrSeatsNumException;
import com.api.Booking.Exception.UserNotFoundException;
import com.api.Booking.Kafka.BookingConfirmation;
import com.api.Booking.Kafka.BookingProducer;
import com.api.Booking.Mapper.BookingMapper;
import com.api.Booking.Model.Booking;
import com.api.Booking.Proxy.FlightServiceFeignClient;
import com.api.Booking.Proxy.UserServiceFeignClient;
import com.api.Booking.Repository.BookingRepository;
import com.api.Booking.Service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightServiceFeignClient flightServiceFeignClient;
    private final UserServiceFeignClient userServiceFeignClient;
    private final BookingMapper bookingMapper;
    private final BookingProducer bookingProducer;

    @CircuitBreaker(name = "bookingServiceCircuitBreaker", fallbackMethod = "fallbackCreateBooking")
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        UserResponse userResponse = userServiceFeignClient.getUserById(bookingRequest.getUserId());
        if (userResponse == null || !userResponse.isActive()) {
            throw new UserNotFoundException("User not found or inactive");
        }

        FlightResponse flightResponse = flightServiceFeignClient.getFlightById(bookingRequest.getFlightId());
        if (flightResponse == null || flightResponse.getAvailableSeats() < bookingRequest.getNumberOfSeats()) {
                throw new FlightOrSeatsNumException("Flight not found or insufficient seats");
        }

        double totalPrice = bookingRequest.getNumberOfSeats() * flightResponse.getPrice();

        Booking booking = bookingMapper.toEntity(bookingRequest, totalPrice);
        Booking savedBooking = bookingRepository.save(booking);

        flightServiceFeignClient.updateAvailableSeats(bookingRequest.getFlightId(), flightResponse.getAvailableSeats() - bookingRequest.getNumberOfSeats());
        BookingResponse response =bookingMapper.toDto(savedBooking);

        BookingConfirmation request = new BookingConfirmation(response.getId()
                , userResponse.getEmail()
                , flightResponse.getId()
                , flightResponse.getDepartureTime()
                , response.getNumberOfSeats()
                , response.getTotalPrice());
        bookingProducer.sendBookingConfirmation(request);

        return response;
    }

    @CircuitBreaker(name = "bookingServiceCircuitBreaker", fallbackMethod = "fallbackGetBookingById")
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        return bookingMapper.toDto(booking);
    }

    public BookingResponse fallbackCreateBooking(BookingRequest bookingRequest, Throwable throwable) {
        return BookingResponse.builder()
                .status("FAILED")
                .TotalPrice(0.0)
                .build();
    }

    public BookingResponse fallbackGetBookingById(Long id, Throwable throwable) {
        return BookingResponse.builder()
                .status("FAILED")
                .TotalPrice(0.0)
                .build();
    }
}
