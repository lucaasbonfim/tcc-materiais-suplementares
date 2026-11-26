package projetoa.domain.funcionario;

import projetoa.domain.funcionario.Funcionario;
import projetoa.domain.funcionario.RoleTipoFuncionarioEnum;
import jakarta.persistence.DiscriminatorColumn;

import java.time.LocalDate;

public record DadosDetalhamentoFuncionario(
        Long id_funcionario,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate data_nascimento,
        int matricula_funcionario,
        String tipo_funcionario
) {
    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(
                funcionario.getId_funcionario(),
                funcionario.getUsuario().getNome(),
                funcionario.getUsuario().getCpf(),
                funcionario.getUsuario().getEmail(),
                funcionario.getUsuario().getTelefone(),
                funcionario.getUsuario().getData_nascimento(),
                funcionario.getMatricula_funcionario(),
                funcionario.getClass().getSimpleName()
        );
    }
}
