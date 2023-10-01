package br.com.fiap.userms.records;

import org.springframework.http.HttpStatus;

public record ExceptionRecord(HttpStatus statusCodeError, String messageError) {
}
