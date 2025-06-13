package br.com.caiquesantos.gestao_vagas.modules.Company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateJobDTO {

    @Schema(example = "Vaga para desenvolvedores", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "GYMPass, Plano de saúde", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "PLENO", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
