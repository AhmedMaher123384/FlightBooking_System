package com.api.Notification.Notification.Kafka;

import com.api.Notification.Email.EmailService;
import com.api.Notification.Notification.Dto.BookingConfirmation;
import com.api.Notification.Notification.Dto.NotificationType;
import com.api.Notification.Notification.Dto.paymentConfirmation;
import com.api.Notification.Notification.Repository.NotificationRepository;
import com.api.Notification.Notification.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consumePaymentNotification(paymentConfirmation request) {
        log.info("Consumed payment notification: {}", request);
        Notification notification = Notification.builder()
                .type(NotificationType.PAYMENT)
                .email(request.email())
                .message("Payment received: " + request.TotalPrice())
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        String emailBody = String.format("Dear %s,\n\nYour payment of %.2f using %s was successful.\n\nPayment ID: %d\n\nThank you for your transaction.",
                request.email(), request.TotalPrice(), request.paymentMethod(), request.paymentId());
        emailService.sendEmail(request.email(), "Payment Notification", emailBody);
    }


    @KafkaListener(topics = "booking-topic", groupId = "notification-group")
    public void consumeBookingNotification(BookingConfirmation bookingConfirmation) {
        log.info("Consumed booking confirmation: {}", bookingConfirmation);
        Notification notification = Notification.builder()
                .type(NotificationType.BOOKING)
                .email(bookingConfirmation.email())
                .message("Booking confirmed for flight ID: " + bookingConfirmation.flightId())
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        String emailBody = String.format("Dear %s,\n\nYour booking for flight ID %d is confirmed.\n\nBooking ID: %d\nFlight ID: %d\nDeparture Time: %s\nNumber of Seats: %d\nTotal Price: %.2f\n\nThank you for booking with us.",
                bookingConfirmation.email(), bookingConfirmation.flightId(), bookingConfirmation.id(), bookingConfirmation.flightId(), bookingConfirmation.departureTime(), bookingConfirmation.numberOfSeats(), bookingConfirmation.TotalPrice());
        emailService.sendEmail(bookingConfirmation.email(), "Booking Confirmation", emailBody);
    }
}