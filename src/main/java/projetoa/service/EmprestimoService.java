package projetoa.service;

import projetoa.domain.biblioteca.*;
import projetoa.domain.usuario.Usuario;
import projetoa.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Emprestimo save(DadosCadastroEmprestimo dados) {
        Livro livro = livroRepository.findById(dados.idLivro()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(dados.idUsuario()).orElseThrow();
        return emprestimoRepository.save(new Emprestimo(dados, livro, usuario));
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }
}
