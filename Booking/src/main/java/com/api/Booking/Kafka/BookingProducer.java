package com.api.Booking.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingProducer {

    private final KafkaTemplate<String, BookingConfirmation> kafkaTemplate;

    public void sendBookingConfirmation(BookingConfirmation bookingConfirmation) {
        log.info("Sending booking confirmation");
        Message<BookingConfirmation> message = MessageBuilder
                .withPayload(bookingConfirmation)
                .setHeader(TOPIC, "booking-topic")
                .build();

        kafkaTemplate.send(message);
    }
}