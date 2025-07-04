package br.com.caiquesantos.gestao_vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Pattern(regexp = "^[^\\s]+$",
            message = "O campo username não deve conter espaços")

    @Schema(example = "RafaelSouza")
    private String username;

    @Schema(example = "rafaelBerloni@gmail.com")
    @Email(message = "O campo deve conter um e-mail válido!")
    private String email;


    @Length(min = 8, max = 100)
    @Schema(example = "example123")
    private String password;

    @Schema(example = "Desenvolvedor de sistemas, atuando na área de DevOps atualmente!")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
