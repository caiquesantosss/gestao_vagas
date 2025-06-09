package br.com.caiquesantos.gestao_vagas.modules.Company.UseCases;

import br.com.caiquesantos.gestao_vagas.modules.Company.Entities.JobEntity;
import br.com.caiquesantos.gestao_vagas.modules.Company.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
