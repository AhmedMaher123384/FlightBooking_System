package com.api.Flight.Exception;

import org.springframework.http.HttpStatus;

public  class FlightNotFoundException extends  RuntimeException {

    public FlightNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
