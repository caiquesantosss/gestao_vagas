package br.com.caiquesantos.gestao_vagas.modules.company.UseCases;

import br.com.caiquesantos.gestao_vagas.exceptions.UserAlredyExistsException;
import br.com.caiquesantos.gestao_vagas.modules.company.Entities.CompanyEntity;
import br.com.caiquesantos.gestao_vagas.modules.company.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(
                companyEntity.getUsername(), companyEntity.getEmail()
        ).ifPresent(user -> {
            throw new UserAlredyExistsException();
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
