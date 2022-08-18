package com.tonyhc.customer;

import com.tonyhc.clients.fraud.FraudCheckHistoryResponse;
import com.tonyhc.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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

        return customer;
    }
}