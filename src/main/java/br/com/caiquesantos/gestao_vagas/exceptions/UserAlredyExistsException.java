package br.com.caiquesantos.gestao_vagas.exceptions;

public class UserAlredyExistsException extends RuntimeException {
    public UserAlredyExistsException() {
        super("Usuário já existe!");
    }
}
