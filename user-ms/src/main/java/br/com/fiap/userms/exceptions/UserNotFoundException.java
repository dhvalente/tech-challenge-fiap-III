package br.com.fiap.powersave.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("Person com ID: %s não encontrado(a)", id));
    }
}
