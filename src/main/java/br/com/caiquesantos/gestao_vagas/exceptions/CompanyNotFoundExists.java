package br.com.caiquesantos.gestao_vagas.exceptions;

public class CompanyNotFoundExists extends RuntimeException {
    public CompanyNotFoundExists() {
        super("Company is not found!");
    }
}
