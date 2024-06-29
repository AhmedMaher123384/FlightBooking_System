package com.api.User.Exception;

import org.springframework.http.HttpStatus;

public  class InvalidEmailException extends  RuntimeException {

    public InvalidEmailException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
