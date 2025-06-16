package br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases;


import br.com.caiquesantos.gestao_vagas.exceptions.*;
import br.com.caiquesantos.gestao_vagas.modules.Company.Entities.*;
import br.com.caiquesantos.gestao_vagas.modules.Company.Repositories.JobRepository;
import br.com.caiquesantos.gestao_vagas.modules.candidate.*;
import br.com.caiquesantos.gestao_vagas.modules.candidate.entities.*;
import br.com.caiquesantos.gestao_vagas.modules.candidate.repositories.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;



    @Test
    @DisplayName("Should not be able to apply to job, if candidate does not exists.")
    public void shouldNotBeAbleToApplyToJob_IfCandidateDoesNotExists() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply to job, if job does not exists. ")
    public void shouldNotBeAbleToApplyToJob_IfJobDoesNotExists() {
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        Mockito.when(candidateRepository.findById(idCandidate))
                .thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should ne able to create a new apply job.")
    public void shouldBeAbleToCreateANewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .jobId(idJob)
                .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        Mockito.when(candidateRepository.findById(idCandidate))
                .thenReturn(Optional.of(new CandidateEntity()));

        Mockito.when(jobRepository.findById(idJob))
                .thenReturn(Optional.of(new JobEntity()));

        // ⚠️ IMPORTANT: Mockito matches objects by reference (==), not by content.
        // This means when(save(obj)) will fail if a different instance (even with same values) is passed.
        // ✅ Solution: use `Mockito.any(ApplyJobEntity.class)` to match any instance of the type,
        // or use `ArgumentCaptor` if you need to verify the saved object's values.

        Mockito.when(applyJobRepository.save(Mockito.any(ApplyJobEntity.class)))
                .thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        Assertions.assertThat(result).hasFieldOrProperty("id");
    }
}
