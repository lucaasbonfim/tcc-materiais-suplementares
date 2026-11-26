package projetoa.controller;

import projetoa.domain.cursoGrade.CursoGrade;
import projetoa.domain.cursoGrade.DadosAtualizaCursoGrade;
import projetoa.domain.cursoGrade.DadosCadastroCursoGrade;
import projetoa.infra.security.exception.CursoGradeNotFoundException;
import projetoa.infra.security.exception.CursoNotFoundException;
import projetoa.service.CursoGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("cursoGrade")
public class CursoGradeController {

    @Autowired
    private CursoGradeService cursoGradeService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarCursoGrade(@RequestBody DadosCadastroCursoGrade dados) {
        CursoGrade cursoGrade = cursoGradeService.cadastraMateriaNoCurso(dados);
        return ResponseEntity.ok(cursoGrade);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizaCursoGrade(@RequestBody DadosAtualizaCursoGrade dados){
        try {
            var cursoGrade = cursoGradeService.cursoGradeAtualizar(dados);

            return ResponseEntity.ok(new DadosAtualizaCursoGrade(cursoGrade));
        } catch (CursoGradeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar")
    @Transactional
    public ResponseEntity deletarCursoGrade(@RequestParam Long idCurso, @RequestParam Long idMateria) {
        try {
            cursoGradeService.deletarCursoGrade(idCurso, idMateria);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CursoGradeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
