package projetoa.domain.curso;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.alunoCurso.AlunoCurso;
import projetoa.domain.cursoGrade.CursoGrade;
import projetoa.domain.materia.Materia;

import java.util.List;

public record DadosDetalhamentoCurso(
        Long id_curso,
        String nome,
        int periodos,
        List<CursoGrade> cursoGrades
) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(
                curso.getId_curso(),
                curso.getNome(),
                curso.getPeriodos(),
                curso.getCursoGrades()
        );
    }
}
