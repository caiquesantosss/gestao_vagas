package br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases;

import br.com.caiquesantos.gestao_vagas.modules.Company.Entities.JobEntity;
import br.com.caiquesantos.gestao_vagas.modules.Company.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
