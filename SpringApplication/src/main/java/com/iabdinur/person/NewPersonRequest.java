package com.iabdinur.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(
        @NotEmpty(message = "Name must not be null or empty") String name,
        @Min(value = 16, message = "Age must be 16 or older") Integer age,
        @NotNull(message = "Gender must not be null") Gender gender,
        @Email(message = "Email must be valid") String email) {
}
