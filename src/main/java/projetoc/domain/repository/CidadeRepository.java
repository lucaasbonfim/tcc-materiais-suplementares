package projetoc.domain.repository;

import projetoc.domain.entity.Cidade;
import projetoc.domain.enums.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByNomeAndUf(String nome, UF uf);
}
