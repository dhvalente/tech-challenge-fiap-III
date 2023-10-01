package br.com.fiap.userms.records;

import br.com.fiap.userms.entity.Address;
import br.com.fiap.userms.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRecord(
        @NotEmpty(message = "{name.required}")String name,
        @NotEmpty(message = "{lastName.required}")String lastName,
        @NotNull(message = "{birthDate.required}") LocalDate birthDate,
        @NotEmpty(message = "{phoneNumber.required}")String phoneNumber,
        @NotNull(message = "{gender.required}") Gender gender,
        @NotNull(message = "{address.required}") Address address
) {
}
