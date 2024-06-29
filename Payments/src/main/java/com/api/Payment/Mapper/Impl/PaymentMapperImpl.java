package com.api.Payment.Mapper.Impl;


import com.api.Payment.Dto.PaymentRequest;
import com.api.Payment.Dto.PaymentResponse;
import com.api.Payment.Mapper.PaymentMapper;
import com.api.Payment.Model.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapperImpl implements PaymentMapper {

    public Payment toEntity(PaymentRequest paymentRequest, double total) {
        return Payment.builder()
                .bookingId(paymentRequest.getBookingId())
                .TotalPrice(total)
                .paymentMethod(paymentRequest.getPaymentMethod())
                .paymentTime(LocalDateTime.now())
                .build();
    }

    public PaymentResponse toDto(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .paymentTime(payment.getPaymentTime())
                .message(payment.getStatus().equals("SUCCESS") ? "Payment successful" : "Payment failed")
                .build();
    }
}