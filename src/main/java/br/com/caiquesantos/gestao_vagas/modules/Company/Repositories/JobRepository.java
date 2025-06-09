package br.com.caiquesantos.gestao_vagas.modules.Company.Repositories;

import br.com.caiquesantos.gestao_vagas.modules.Company.Entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
