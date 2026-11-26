package projetoa.domain.alunoCurso;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.curso.Curso;

public record DadosCadastroAlunoCurso(
        Aluno aluno,
        Curso curso,
        int status,
        String ano_matricula
) {
}
