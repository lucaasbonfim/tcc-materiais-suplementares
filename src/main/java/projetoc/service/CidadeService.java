package projetoc.service;

import projetoc.domain.entity.Cidade;
import projetoc.domain.repository.CidadeRepository;
import projetoc.dto.CidadeRequestDTO;
import projetoc.dto.CidadeResponseDTO;
import projetoc.exception.ConflictException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional(readOnly = true)
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CidadeResponseDTO findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com ID: " + id));
        return toDTO(cidade);
    }

    @Transactional
    public CidadeResponseDTO create(CidadeRequestDTO dto) {
        if (cidadeRepository.findByNomeAndUf(dto.nome(), dto.uf()).isPresent()) {
            throw new ConflictException("Já existe uma cidade cadastrada com este nome e UF.");
        }

        Cidade cidade = new Cidade();
        cidade.setNome(dto.nome());
        cidade.setUf(dto.uf());

        Cidade saved = cidadeRepository.save(cidade);
        return toDTO(saved);
    }

    @Transactional
    public CidadeResponseDTO update(Long id, CidadeRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com ID: " + id));

        // Check if there is another city with the same name/uf
        cidadeRepository.findByNomeAndUf(dto.nome(), dto.uf())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new ConflictException("Já existe outra cidade cadastrada com este nome e UF.");
                });

        cidade.setNome(dto.nome());
        cidade.setUf(dto.uf());

        Cidade updated = cidadeRepository.save(cidade);
        return toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!cidadeRepository.existsById(id)) {
            throw new RuntimeException("Cidade não encontrada com ID: " + id);
        }
        cidadeRepository.deleteById(id);
    }

    private CidadeResponseDTO toDTO(Cidade cidade) {
        return new CidadeResponseDTO(cidade);
    }
}
