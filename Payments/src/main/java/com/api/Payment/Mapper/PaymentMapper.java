package com.api.Payment.Mapper;

import com.api.Payment.Dto.PaymentRequest;
import com.api.Payment.Dto.PaymentResponse;
import com.api.Payment.Model.Payment;

public interface PaymentMapper {
    Payment toEntity(PaymentRequest paymentRequest, double amount);
    PaymentResponse toDto(Payment payment);
}
