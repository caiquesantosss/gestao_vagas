package br.com.caiquesantos.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor java")
    private String description;

    @Schema(example = "ErickGonçalves")
    private String username;

    @Schema(example = "erickgoncalves@gmail.com")
    private String email;

    @Schema(example = "Erick Gonçalves")
    private String name;
    private UUID id;
}
