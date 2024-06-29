package com.api.Payment.Service;

import com.api.Payment.Dto.PaymentRequest;
import com.api.Payment.Dto.PaymentResponse;
import com.api.Payment.Model.Payment;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
    Payment getPaymentById(Long id);
}
