package br.com.fiap.userms.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("User com ID: %s não encontrado(a)", id));
    }
}
