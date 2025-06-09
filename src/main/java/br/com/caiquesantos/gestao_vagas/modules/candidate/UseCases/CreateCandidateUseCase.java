package br.com.caiquesantos.gestao_vagas.modules.candidate.UseCases;

import br.com.caiquesantos.gestao_vagas.exceptions.UserAlredyExistsException;
import br.com.caiquesantos.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.caiquesantos.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameAndEmail(
                candidateEntity.getUsername(), candidateEntity.getEmail()
        ).ifPresent((user) -> {
            throw new UserAlredyExistsException();
        });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
