package com.api.Payment.Controller;


import com.api.Payment.Dto.PaymentRequest;
import com.api.Payment.Dto.PaymentResponse;
import com.api.Payment.Model.Payment;
import com.api.Payment.Service.Impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImpl;

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentServiceImpl.processPayment(paymentRequest);
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment paymentResponse = paymentServiceImpl.getPaymentById(id);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

}