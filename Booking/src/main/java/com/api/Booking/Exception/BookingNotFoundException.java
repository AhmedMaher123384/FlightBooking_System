package com.api.Booking.Exception;

import org.springframework.http.HttpStatus;

public  class BookingNotFoundException extends  RuntimeException {

    public BookingNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
