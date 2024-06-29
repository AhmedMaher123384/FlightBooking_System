package com.api.Payment.Exception;

import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends  RuntimeException {

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
