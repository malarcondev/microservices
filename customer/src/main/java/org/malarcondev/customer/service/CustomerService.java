package org.malarcondev.customer.service;

import org.malarcondev.customer.dto.CustomerRegistrationRequest;
import org.malarcondev.customer.entity.Customer;
import org.malarcondev.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();

        // TODO: check if email valid
        // TODO: check if email not taken
        customerRepository.save(customer);
    }
}
