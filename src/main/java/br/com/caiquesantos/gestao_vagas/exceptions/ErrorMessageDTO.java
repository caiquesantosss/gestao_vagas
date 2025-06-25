package br.com.caiquesantos.gestao_vagas.exceptions;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDTO {
    private String message;
    private String field;
}
