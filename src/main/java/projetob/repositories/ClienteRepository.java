package projetob.repositories;

import projetob.models.Cliente;
import projetob.webservices.responses.ClienteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("""
    SELECT new projetob.webservices.responses.ClienteResponse(c.id, c.usuario.nome, c.usuario.cpf, c.usuario.email, c.usuario.telefone,
    c.usuario.dataNascimento, c.cnh, c.apolice, c.endereco.cep, c.endereco.logradouro, c.endereco.numero, c.endereco.complemento, c.endereco.cidade,
    c.endereco.estado) FROM Cliente c
    WHERE c.usuario.cpf = :cpf
    """)
    Optional<ClienteResponse> findByCpf(@Param("cpf") String cpf);
}
