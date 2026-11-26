package projetoa.domain.turma;

public record DadosAtualizaTurma(
        Long id_turma,
        Long id_funcionario,
        Long id_materia,
        String semestre_turma
) {
}
