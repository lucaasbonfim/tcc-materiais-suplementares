package projetoa.domain.financeiro;

import java.util.Date;

public record DadosCadastroBoleto(
        Long idUsuario,
        Float valor,
        Date dataCriacao,
        Date dataVencimento,
        Float juros,
        TipoBoletoEnum tipoBoleto
) {}