package projetoa.domain.financeiro;

import java.util.Date;

public record DadosCadastroMensalidadeAluno(
        Long idAluno,
        Float valor,
        Date dataCriacao
) {}