package projetoa.domain.nota;

import projetoa.domain.aluno.Aluno;

public record DadosCadastroNota(
        Long idAluno,
        Long idTurma,
        int p1,
        int p2,
        int pf


) {
}
