package com.api.Notification.Notification.Dto;


public record paymentConfirmation(Long paymentId,
                                  double TotalPrice,
                                  PaymentMethod paymentMethod,
                                  String message,
                                  String email) {
}
