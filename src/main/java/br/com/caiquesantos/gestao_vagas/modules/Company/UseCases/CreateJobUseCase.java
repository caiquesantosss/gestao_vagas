package br.com.caiquesantos.gestao_vagas.modules.company.UseCases;

import br.com.caiquesantos.gestao_vagas.exceptions.*;
import br.com.caiquesantos.gestao_vagas.modules.company.Entities.JobEntity;
import br.com.caiquesantos.gestao_vagas.modules.company.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundExists();
        });

        return this.jobRepository.save(jobEntity);
    }
}
