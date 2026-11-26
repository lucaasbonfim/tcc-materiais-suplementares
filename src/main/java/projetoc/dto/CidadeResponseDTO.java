package projetoc.dto;

import projetoc.domain.entity.Cidade;
import projetoc.domain.enums.UF;
import io.swagger.v3.oas.annotations.media.Schema;

public record CidadeResponseDTO(
        @Schema(example = "1")
        Long id,
        
        @Schema(example = "Belo Horizonte")
        String nome,
        
        @Schema(example = "MG")
        UF uf
) {
    public CidadeResponseDTO(Cidade cidade) {
        this(
                cidade.getId(),
                cidade.getNome(),
                cidade.getUf()
        );
    }
}
