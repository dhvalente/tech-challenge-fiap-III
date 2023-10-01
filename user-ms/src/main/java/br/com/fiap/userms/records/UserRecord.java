package br.com.fiap.userms.records;

import br.com.fiap.userms.entity.Address;
import br.com.fiap.userms.enums.Gender;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record UserRecord(
        @NotEmpty(message = "{name.required}")String name,
        @NotEmpty(message = "{lastName.required}")String lastName,
        @NotEmpty(message = "{birthDate.required}") LocalDate birthDate,
        @NotEmpty(message = "{phoneNumber.required}")String phoneNumber,
        @NotEmpty(message = "{gender.required}") Gender gender,
        @NotEmpty(message = "{address.required}") Address address
) {
}
