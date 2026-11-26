package projetoa.domain.turmaAlunoNota;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.alunoCurso.AlunoCursoId;
import projetoa.domain.alunoTurma.AlunoTurma;
import projetoa.domain.curso.Curso;
import projetoa.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TURMA_ALUNO_NOTA")
@Entity(name = "TurmaAlunoNota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TurmaAlunoNota {

    @Id
    @EmbeddedId
    private TurmaAlunoNotaId id;

    @ManyToOne
    @MapsId("idAluno")
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @MapsId("idTurma")
    @JoinColumn(name = "id_turma")
    private Turma turma;


    private Float p1;
    private Float p2;
    private Float pf;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno"),
            @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
    })
    private AlunoTurma alunoTurma;

    public TurmaAlunoNota(Aluno aluno, Turma turma) {
        TurmaAlunoNotaId id = new TurmaAlunoNotaId(aluno.getId_aluno(), turma.getId_turma());

        this.id = id;
        this.aluno = aluno;
        this.turma = turma;
        this.p1 = null;
        this.p2 = null;
        this.pf = null;
    }

    public void alterarNotas(DadosAtualizaTurmaAlunoNota dados) {
        if(dados.p1() != null) {
            this.p1 = dados.p1();
        }

        if(dados.p2() != null) {
            this.p2 = dados.p2();
        }

        if(dados.pf() != null) {
            this.pf = dados.pf();
        }
    }
}



