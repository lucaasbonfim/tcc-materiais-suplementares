package projetoa.controller;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.aluno.DadosDetalhamentoAluno;
import projetoa.domain.alunoCurso.AlunoCurso;
import projetoa.domain.alunoCurso.AlunoCursoRepository;
import projetoa.domain.alunoCurso.DadosCadastroAlunoCurso;
import projetoa.domain.curso.Curso;
import projetoa.service.AlunoService;
import projetoa.service.CursoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunoCurso")
public class AlunoCursoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoCursoRepository alunoCursoRepository;

    @Autowired
    private CursoService cursoService;

    @PostMapping("/{id_aluno}/{id_curso}")
    public ResponseEntity cadastrarAlunoCurso(@PathVariable Long id_aluno, @PathVariable Long id_curso, @RequestParam  String ano_matricula) {
        Aluno aluno = alunoService.pegarUmAluno(id_aluno);
        Curso curso = cursoService.buscarCursoPorId(id_curso);

        AlunoCurso alunoCurso = new AlunoCurso(aluno, curso, 1, ano_matricula);
        alunoCursoRepository.save(alunoCurso);

        return ResponseEntity.ok(alunoCurso);
    }
}
