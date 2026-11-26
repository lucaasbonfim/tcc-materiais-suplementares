package projetoa.controller;

import projetoa.domain.aluno.*;
import projetoa.domain.alunoCurso.AlunoCurso;
import projetoa.domain.alunoCurso.AlunoCursoRepository;
import projetoa.domain.alunoCurso.DadosCadastroAlunoCurso;
import projetoa.domain.curso.Curso;
import projetoa.infra.security.exception.CursoNotFoundException;
import projetoa.service.AlunoService;
import projetoa.service.CursoService;
import projetoa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {


    @Autowired
    private AlunoService alunoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/teste")
    public String teste(){
        return "Wololo";
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemAluno>> listarTodos(){

        List<DadosListagemAluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);

    }

    @PostMapping
    public ResponseEntity cadastrarAluno(@RequestBody DadosCadastroAluno dadosCadastroAluno,@RequestParam Long idUsuario) {
        var usuarioOptional = usuarioService.encontrarUsuario(idUsuario);


        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();

            try {
                Aluno novoAluno = new Aluno(dadosCadastroAluno, usuario);
                alunoService.salvarAluno(novoAluno);

                return ResponseEntity.ok(new DadosDetalhamentoAluno(novoAluno));
            } catch (CursoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id " + idUsuario + " não encontrado");
        }
    }
//    public ResponseEntity listar(){
//        var alunos = alunoRepository.findAll();
//        return ResponseEntity.ok(alunos);
//    }

    @GetMapping("/{id}")
    public ResponseEntity detalharAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.detalharaluno(id);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity detalharAlunoPorUsuario(@PathVariable Long id) {
        Aluno aluno = alunoService.detalharAlunoProUsuario(id);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizaAluno(@RequestBody DadosAtualizaAluno dados){
        try {
            var aluno = alunoService.atualizarAluno(dados);
            return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
        } catch (CursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
