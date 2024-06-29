package com.api.Booking.Exception;

import org.springframework.http.HttpStatus;

public  class FlightOrSeatsNumException extends  RuntimeException {

    public FlightOrSeatsNumException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
