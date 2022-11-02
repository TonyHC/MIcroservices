package com.tonyhc.customer;

import com.tonyhc.ampq.RabbitMQMessageProducer;
import com.tonyhc.clients.fraud.FraudCheckHistoryResponse;
import com.tonyhc.clients.fraud.FraudClient;
import com.tonyhc.clients.notification.NotificationRequest;
import com.tonyhc.kafka.KafkaNotificationProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final KafkaNotificationProducer kafkaNotificationProducer;

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckHistoryResponse fraudCheckHistoryResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckHistoryResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("%s %s", customer.getFirstName(), customer.getLastName()),
                String.format("Hi %s, welcome to Fraud Services", customer.getFirstName())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        kafkaNotificationProducer.publish("notification", notificationRequest);

        return customer;
    }
}