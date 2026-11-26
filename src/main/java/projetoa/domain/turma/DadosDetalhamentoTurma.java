package projetoa.domain.turma;

import projetoa.domain.alunoTurma.AlunoTurma;
import projetoa.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import projetoa.domain.turmaAlunoNota.TurmaAlunoNota;

import java.util.Date;
import java.util.List;

public record DadosDetalhamentoTurma(
        Long id_turma,
        Long id_funcionario,
        String nome_professor,
        Long id_materia,
        String nome_materia,
        String semestre_turma
) {
    public DadosDetalhamentoTurma(Turma turma){
        this(
                turma.getId_turma(),
                turma.getProfessor().getId_funcionario(),
                turma.getProfessor().getUsuario().getNome(),
                turma.getMateria().getId_materia(),
                turma.getMateria().getNome(),
                turma.getSemestre_turma()
        );
    }
}
