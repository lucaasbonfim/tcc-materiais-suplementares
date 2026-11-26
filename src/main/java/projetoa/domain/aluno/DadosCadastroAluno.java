package projetoa.domain.aluno;

import projetoa.domain.curso.Curso;
import projetoa.domain.usuario.DadosCadastroUsuario;
import projetoa.domain.usuario.Usuario;

import java.util.List;

public record DadosCadastroAluno(
        Usuario usuario,
        int matricula_aluno,
        int status,
        String ano_matricula
//        List<Curso> cursos
) {

}
