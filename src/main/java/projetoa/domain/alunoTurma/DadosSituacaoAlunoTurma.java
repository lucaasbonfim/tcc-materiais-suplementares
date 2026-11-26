package projetoa.domain.alunoTurma;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.turma.Turma;

public record DadosSituacaoAlunoTurma(
        String nomeAluno,
        String nomeTurma,
        String situacao
) {
//    public DadosSituacaoAlunoTurma(AlunoTurma alunoTurma) {
//        this(alunoTurma.getAluno().getUsuario().getNome(), alunoTurma.getTurma().getNome(), alunoTurma.getSituacao());
//    }
}
