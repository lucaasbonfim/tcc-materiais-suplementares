package projetoc.strategy;

import projetoc.domain.entity.Pagamento;
import projetoc.domain.enums.TipoPagamento;

public interface PagamentoStrategy {
    
    boolean processar(Pagamento pagamento);

    boolean seAplicaA(TipoPagamento tipoPagamento);

}
