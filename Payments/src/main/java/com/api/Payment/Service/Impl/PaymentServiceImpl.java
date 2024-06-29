package com.api.Payment.Service.Impl;

import com.api.Payment.Dto.BookingResponse;
import com.api.Payment.Dto.PaymentRequest;
import com.api.Payment.Dto.PaymentResponse;
import com.api.Payment.Dto.UserResponse;
import com.api.Payment.Exception.BookingNotFoundException;
import com.api.Payment.Exception.PaymentNotFoundException;
import com.api.Payment.Kafka.NotificationProducer;
import com.api.Payment.Kafka.paymentConfirmation;
import com.api.Payment.Mapper.PaymentMapper;
import com.api.Payment.Model.Payment;
import com.api.Payment.Proxy.BookingServiceFeignClient;
import com.api.Payment.Proxy.UserServiceFeignClient;
import com.api.Payment.Repository.PaymentRepository;
import com.api.Payment.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingServiceFeignClient bookingServiceFeignClient;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    private final UserServiceFeignClient userServiceFeignClient;

    @CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "fallbackProcessPayment")
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {

        BookingResponse bookingResponse = bookingServiceFeignClient.getBookingById(paymentRequest.getBookingId());
        UserResponse userResponse = userServiceFeignClient.getUserById(bookingResponse.getUserId());

        if (bookingResponse== null)
            throw new BookingNotFoundException("Booking not found");

        double amount = bookingResponse.getTotalPrice();

        Payment payment = paymentMapper.toEntity(paymentRequest, amount);
        payment.setPaymentTime(LocalDateTime.now());


        payment.setStatus("SUCCESS");

        Payment savedPayment = paymentRepository.save(payment);
        PaymentResponse response = paymentMapper.toDto(savedPayment);

        paymentConfirmation request =new paymentConfirmation(payment.getId(),
                savedPayment.getTotalPrice(),
                paymentRequest.getPaymentMethod(),
                response.getMessage(),
                userResponse.getEmail()
                );

        notificationProducer.sendNotification(request);
        return response;
    }


    @CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "fallbackGetPaymentById")
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }



    public PaymentResponse fallbackProcessPayment(PaymentRequest paymentRequest, Throwable throwable) {
        return PaymentResponse.builder()
                .paymentId(null)
                .status("FAILED")
                .paymentTime(LocalDateTime.now())
                .message("Fallback payment")
                .build();
    }

    public PaymentResponse fallbackGetPaymentById(Long id, Throwable throwable) {
        return PaymentResponse.builder()
                .paymentId(id)
                .status("FAILED")
                .paymentTime(LocalDateTime.now())
                .message("Fallback payment")
                .build();
    }
}