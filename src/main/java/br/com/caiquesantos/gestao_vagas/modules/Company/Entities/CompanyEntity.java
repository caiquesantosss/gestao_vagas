package br.com.caiquesantos.gestao_vagas.modules.Company.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O username não pode ter espaços!")
    private String username;

    @Length(min = 8, max = 110, message = "A senha deve conter entre 8 e 20 caracteres!")
    private String password;

    @Email(message = "O campo deve conter um e-mail válido!")
    private String email;
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
