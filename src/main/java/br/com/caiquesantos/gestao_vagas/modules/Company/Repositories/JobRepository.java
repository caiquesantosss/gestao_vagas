package br.com.caiquesantos.gestao_vagas.modules.company.Repositories;

import br.com.caiquesantos.gestao_vagas.modules.company.Entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
