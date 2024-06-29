package com.api.Payment.Kafka;


import com.api.Payment.Dto.PaymentMethod;

public record paymentConfirmation(Long paymentId,
                                  double TotalPrice,
                                  PaymentMethod paymentMethod,
                                  String message,
                                  String email) {
}
