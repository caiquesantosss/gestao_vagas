package br.com.caiquesantos.gestao_vagas.modules.candidate.repositories;

import br.com.caiquesantos.gestao_vagas.modules.candidate.entities.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
