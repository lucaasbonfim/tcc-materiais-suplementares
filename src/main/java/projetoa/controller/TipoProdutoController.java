package projetoa.controller;

import projetoa.domain.almoxarifado.DadoCadastroTipoProduto;
import projetoa.domain.almoxarifado.TipoProduto;
import projetoa.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoProduto")
public class TipoProdutoController {

    @Autowired
    TipoProdutoService tipoProdutoService;

    @PostMapping
    public ResponseEntity cadastrarTipoProduto(@RequestBody DadoCadastroTipoProduto dado) {

        TipoProduto tipoProduto = new TipoProduto(dado.nome());

        tipoProdutoService.salvarProduto(tipoProduto);

        return ResponseEntity.ok(tipoProduto);
    }

    @GetMapping
    public List<TipoProduto> listarTipoProduto() {
        return tipoProdutoService.listarTipoProduto();
    }

}
