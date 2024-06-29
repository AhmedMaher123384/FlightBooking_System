package com.api.Flight.Exception;

import org.springframework.http.HttpStatus;

public  class RouteNotFoundException extends  RuntimeException {

    public RouteNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
