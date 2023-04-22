package org.malarcondev.customer.service;

import lombok.AllArgsConstructor;
import org.malarcondev.clients.fraud.FraudCheckResponse;
import org.malarcondev.clients.fraud.FraudClient;
import org.malarcondev.clients.notification.NotificationClient;
import org.malarcondev.clients.notification.NotificationRequest;
import org.malarcondev.customer.dto.CustomerRegistrationRequest;
import org.malarcondev.customer.entity.Customer;
import org.malarcondev.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();

        // TODO: check if email valid
        // TODO: check if email not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // TODO: send notification
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, Welcome...", customer.getFirstname())
                )
        );
    }
}
