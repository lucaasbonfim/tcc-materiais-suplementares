package projetoa.service;

import projetoa.domain.alunoTurma.AlunoTurma;
import projetoa.domain.alunoTurma.AlunoTurmaRepository;
import projetoa.domain.alunoTurma.DadosDetalhamentoAlunoTurma;
import projetoa.domain.alunoTurma.TurmaAlunoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoTurmaService {

        @Autowired
        private AlunoTurmaRepository alunoTurmaRepository;

    public AlunoTurma salvarAlunoTurma(AlunoTurma alunoTurma) {
        return alunoTurmaRepository.save(alunoTurma);
    }

    public List<DadosDetalhamentoAlunoTurma> buscarAlunosPorTurma(Long id) {
        return alunoTurmaRepository.findByIdTurma(id).stream()
                .map(DadosDetalhamentoAlunoTurma::new)
                .collect(Collectors.toList());
    }

    public List<DadosDetalhamentoAlunoTurma> buscarAlunosTurmaPorAluno(Long id) {
        return alunoTurmaRepository.findByIdAluno(id).stream()
                .map(DadosDetalhamentoAlunoTurma::new)
                .collect(Collectors.toList());
    }

    public AlunoTurma buscarAlunoPorTurma(Long id_aluno, Long id_turma) {
        TurmaAlunoId turmaAlunoId = new TurmaAlunoId(id_aluno, id_turma);

        return alunoTurmaRepository.findById(turmaAlunoId)
                .orElseThrow(() -> new RuntimeException("Turma ou aluno não encontrada"));
    }

    public AlunoTurma trancarMatricula(AlunoTurma alunoTurma) {
        alunoTurma.trancarMatricula();

        alunoTurmaRepository.save(alunoTurma);

        return alunoTurma;
    }

    public void deletarAlunoTurma(AlunoTurma alunoTurma) {
        alunoTurmaRepository.delete(alunoTurma);
    }



}
