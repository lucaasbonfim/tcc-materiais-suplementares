package projetoa.service;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.aluno.DadosAtualizaAluno;
import projetoa.domain.curso.Curso;
import projetoa.domain.curso.CursoRepository;
import projetoa.domain.cursoGrade.*;
import projetoa.domain.materia.Materia;
import projetoa.domain.materia.MateriaRepository;
import projetoa.infra.security.exception.CursoGradeNotFoundException;
import projetoa.infra.security.exception.CursoNotFoundException;
import projetoa.infra.security.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    CursoGradeService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private CursoGradeRepository cursoGradeRepository;

    public CursoGrade cadastraMateriaNoCurso(DadosCadastroCursoGrade dados) {
        Curso curso = cursoRepository.findById(dados.id_curso())
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + dados.id_curso() + " não encontrado"));

        Materia materia = materiaRepository.findById(dados.id_materia())
                .orElseThrow(() -> new MateriaNotFoundException("Matéria com id " + dados.id_materia() + " não encontrada"));

        CursoGrade cursoGrade = new CursoGrade(dados, curso, materia);
        return cursoGradeRepository.save(cursoGrade);
    }

    public CursoGrade cursoGradeAtualizar(DadosAtualizaCursoGrade dados){

        CursoGradeId cursoGradeId = new CursoGradeId(dados.id_curso(), dados.id_materia());

        CursoGrade cursoGrade = cursoGradeRepository.findById(cursoGradeId)
                .orElseThrow(() -> new CursoGradeNotFoundException("Esse curso não esta presente na grade"));

        cursoGrade.atualizaPeriodo(dados.periodo());

        cursoGradeRepository.save(cursoGrade);

        return cursoGrade;
    }

    public void deletarCursoGrade(Long idCurso, Long idMateria) {
        CursoGradeId cursoGradeId = new CursoGradeId(idCurso, idMateria);
        CursoGrade cursoGrade = cursoGradeRepository.findById(cursoGradeId)
                .orElseThrow(() -> new CursoGradeNotFoundException("Esse curso não está presente na grade"));
        cursoGradeRepository.delete(cursoGrade);
    }
}
