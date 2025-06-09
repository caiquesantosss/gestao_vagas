package br.com.caiquesantos.gestao_vagas.modules.Company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateJobDTO {
    private String description;
    private String benefits;
    private String level;
}
