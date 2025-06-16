package br.com.caiquesantos.gestao_vagas.modules.candidate.entities;

import br.com.caiquesantos.gestao_vagas.modules.Company.Entities.*;
import br.com.caiquesantos.gestao_vagas.modules.candidate.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "apply_jobs")
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private CandidateEntity candidateEntity;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity jobEntity;

    @Column(name = "candidate_id")
    private UUID candidateId;

    @Column(name = "job_id")
    private UUID jobId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
