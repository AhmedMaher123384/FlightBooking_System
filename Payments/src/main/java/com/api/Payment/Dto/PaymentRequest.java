package com.api.Payment.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentRequest {
    @NotEmpty(message = "Booking ID is required")
    private Long bookingId;

    @NotEmpty(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}