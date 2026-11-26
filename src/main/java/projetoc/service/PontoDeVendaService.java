package projetoc.service;

import projetoc.domain.entity.Funcionario;
import projetoc.domain.entity.PontoDeVenda;
import projetoc.domain.repository.FuncionarioRepository;
import projetoc.domain.repository.PontoDeVendaRepository;
import projetoc.dto.PontoDeVendaRequestDTO;
import projetoc.dto.PontoDeVendaResponseDTO;
import projetoc.exception.ConflictException;
import projetoc.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PontoDeVendaService {

    @Autowired
    private PontoDeVendaRepository pontoDeVendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public PontoDeVendaResponseDTO criar(PontoDeVendaRequestDTO dto) {
        if (pontoDeVendaRepository.findByCnpj(dto.cnpj()).isPresent()) {
            throw new ConflictException("Já existe um Ponto de Venda com este CNPJ");
        }

        Funcionario gerente = funcionarioRepository.findById(dto.gerenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Gerente (Funcionario)", "id", dto.gerenteId()));

        PontoDeVenda pdv = new PontoDeVenda();
        pdv.setCnpj(dto.cnpj());
        pdv.setEndereco(dto.endereco());
        pdv.setGerente(gerente);

        PontoDeVenda saved = pontoDeVendaRepository.save(pdv);
        return new PontoDeVendaResponseDTO(saved);
    }

    public List<PontoDeVendaResponseDTO> listarTodos() {
        return pontoDeVendaRepository.findAll().stream()
                .map(PontoDeVendaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PontoDeVendaResponseDTO buscarPorId(Long id) {
        PontoDeVenda pdv = pontoDeVendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de Venda", "id", id));
        return new PontoDeVendaResponseDTO(pdv);
    }

    public PontoDeVendaResponseDTO atualizar(Long id, PontoDeVendaRequestDTO dto) {
        PontoDeVenda pdv = pontoDeVendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de Venda", "id", id));

        if (!pdv.getCnpj().equals(dto.cnpj()) && 
            pontoDeVendaRepository.findByCnpj(dto.cnpj()).isPresent()) {
            throw new ConflictException("Já existe outro Ponto de Venda com este CNPJ");
        }

        Funcionario gerente = funcionarioRepository.findById(dto.gerenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Gerente (Funcionario)", "id", dto.gerenteId()));

        pdv.setCnpj(dto.cnpj());
        pdv.setEndereco(dto.endereco());
        pdv.setGerente(gerente);

        PontoDeVenda updated = pontoDeVendaRepository.save(pdv);
        return new PontoDeVendaResponseDTO(updated);
    }

    public void excluir(Long id) {
        PontoDeVenda pdv = pontoDeVendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de Venda", "id", id));
        pontoDeVendaRepository.delete(pdv);
    }

    public PontoDeVendaResponseDTO atribuirGerente(Long id, Long gerenteId) {
        PontoDeVenda pdv = pontoDeVendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de Venda", "id", id));

        Funcionario gerente = funcionarioRepository.findById(gerenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Gerente (Funcionario)", "id", gerenteId));

        pdv.setGerente(gerente);

        PontoDeVenda updated = pontoDeVendaRepository.save(pdv);
        return new PontoDeVendaResponseDTO(updated);
    }
}
