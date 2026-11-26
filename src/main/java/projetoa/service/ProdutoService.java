package projetoa.service;

import projetoa.domain.almoxarifado.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public Produto save(DadosCadastroProduto dados) {
        TipoProduto tipoProduto = tipoProdutoRepository.findById(dados.id_tipo_produto()).orElseThrow();
        return produtoRepository.save(new Produto(dados, tipoProduto));
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

}
