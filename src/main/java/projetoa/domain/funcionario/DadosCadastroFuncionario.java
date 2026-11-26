package projetoa.domain.funcionario;

import projetoa.domain.usuario.Usuario;

public record DadosCadastroFuncionario(
        Usuario usuario,
        String matricula_funcionario
) {
}
