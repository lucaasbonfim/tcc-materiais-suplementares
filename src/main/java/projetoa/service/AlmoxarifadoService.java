package projetoa.service;

import projetoa.domain.almoxarifado.Almoxarifado;
import projetoa.domain.almoxarifado.AlmoxarifadoRepository;
import projetoa.domain.aluno.DadosListagemAluno;
import projetoa.domain.funcionario.DadosDetalhamentoFuncionario;
import projetoa.infra.security.exception.FuncionarioRoleWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlmoxarifadoService {

    @Autowired
    AlmoxarifadoRepository almoxarifadoRepository;

    public Almoxarifado salvarAlmoxarifado(Almoxarifado almoxarifado) {
        if(almoxarifado.isAlmoxarifado(almoxarifado.getId_funcionario())) {
            return almoxarifadoRepository.save(almoxarifado);
        } else {
            throw new FuncionarioRoleWrongException("O usuário não tem o papel de ALMOXARIFE");
        }
    }

    public List<DadosDetalhamentoFuncionario> buscarTodos() {
        return almoxarifadoRepository.findAll().stream()
                .map(DadosDetalhamentoFuncionario::new)
                .collect(Collectors.toList());
    }
}
