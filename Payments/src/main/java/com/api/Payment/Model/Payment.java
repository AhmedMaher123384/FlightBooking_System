package com.api.Payment.Model;

import com.api.Payment.Dto.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookingId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private double TotalPrice;
    private String status;
    private LocalDateTime paymentTime;
}



