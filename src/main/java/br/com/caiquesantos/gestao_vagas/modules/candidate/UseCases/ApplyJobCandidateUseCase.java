package br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases;

import br.com.caiquesantos.gestao_vagas.exceptions.JobNotFoundException;
import br.com.caiquesantos.gestao_vagas.exceptions.UserNotFoundException;
import br.com.caiquesantos.gestao_vagas.modules.Company.Repositories.JobRepository;
import br.com.caiquesantos.gestao_vagas.modules.candidate.entities.*;
import br.com.caiquesantos.gestao_vagas.modules.candidate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository; 

    @Autowired
    JobRepository jobRepository;

    public ApplyJobEntity execute(UUID IdCandidate, UUID IdJob) {
        this.candidateRepository.findById(IdCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        this.jobRepository.findById(IdJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        var applyJob = ApplyJobEntity.builder()
                .candidateId(IdCandidate).jobId(IdJob).build();

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }
}
