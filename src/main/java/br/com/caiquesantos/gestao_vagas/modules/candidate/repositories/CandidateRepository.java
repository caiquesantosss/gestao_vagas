package br.com.caiquesantos.gestao_vagas.modules.candidate.repositories;

import br.com.caiquesantos.gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameAndEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
}

