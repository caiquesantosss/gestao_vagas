package br.com.caiquesantos.gestao_vagas.modules.candidate.controllers;

import br.com.caiquesantos.gestao_vagas.exceptions.UserAlredyExistsException;
import br.com.caiquesantos.gestao_vagas.modules.Company.Entities.JobEntity;
import br.com.caiquesantos.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases.CreateCandidateUseCase;
import br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases.ListAllJobsByFilterUseCase;
import br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases.ProfileCandidateUseCase;
import br.com.caiquesantos.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.caiquesantos.gestao_vagas.modules.candidate.entities.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Candidato", description = "Informações sobre o usuário")
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    @Operation(
            summary = "Cadastro de candidatos",
            description = "Esta função é responsável por cadastrar um candidato "
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CandidateEntity.class))
            }),

            @ApiResponse(responseCode = "400", description = "Usuário já existe!")
    })

    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
            summary = "Perfil do usuário",
            description = "Essa função é responsável por buscar as informações do perfil do candidato"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(
                            implementation = ProfileCandidateResponseDTO.class
                    ))
            }),
            @ApiResponse(responseCode = "400", description = "User not found!")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase.execute(
                    UUID.fromString(idCandidate.toString())
            );

            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
            summary = "Listagem de vagas disponíveis para o candidato",
            description = "Esta função lista todos as vagas disponíveis atráves de um filtro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(
                            implementation = JobEntity.class
                    )))
            })
    })

    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
