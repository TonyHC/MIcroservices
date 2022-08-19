package com.tonyhc.customer;

import com.tonyhc.clients.fraud.FraudCheckHistoryResponse;
import com.tonyhc.clients.fraud.FraudClient;
import com.tonyhc.clients.notification.NotificationClient;
import com.tonyhc.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

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

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("%s %s", customer.getFirstName(), customer.getLastName()),
                        String.format("Hi %s, welcome to Fraud Services", customer.getFirstName())
                )
        );

        return customer;
    }
}