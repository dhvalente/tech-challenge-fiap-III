package br.com.fiap.userms.records;

import org.springframework.http.HttpStatus;

public record ExceptionValidationRecord(HttpStatus statusCodeError, java.util.Map<String, String> messageError) {
}
