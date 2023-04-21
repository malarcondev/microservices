package org.malarcondev.customer.dto;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email) {
}
