package projetoa.service;

import projetoa.domain.turmaAlunoNota.TurmaAlunoNota;
import projetoa.domain.turmaAlunoNota.TurmaAlunoNotaId;
import projetoa.domain.turmaAlunoNota.TurmaAlunoNotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaAlunoNotaService {

    @Autowired
    private TurmaAlunoNotaRepository turmaAlunoNotaRepository;

    public TurmaAlunoNota  salvarAlunoENota(TurmaAlunoNota turmaAlunoNota) {
        return turmaAlunoNotaRepository.save(turmaAlunoNota);
    }

    @Transactional
    public TurmaAlunoNota encontrarRegistro(TurmaAlunoNotaId turmaAlunoNotaId) {
        TurmaAlunoNota turmaAlunoNota = turmaAlunoNotaRepository.findById(turmaAlunoNotaId)
                .orElseThrow(() -> new RuntimeException("Aluno ou Turma não encontrado"));

        return turmaAlunoNota;
    }

}
