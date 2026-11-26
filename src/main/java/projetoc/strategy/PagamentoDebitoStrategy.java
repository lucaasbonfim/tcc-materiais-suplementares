package projetoc.strategy;

import projetoc.domain.entity.Pagamento;
import projetoc.domain.entity.PagamentoCredito;
import projetoc.domain.enums.TipoPagamento;

import org.springframework.stereotype.Component;

@Component
public class PagamentoDebitoStrategy implements PagamentoStrategy {
    
    @Override
    public boolean processar(Pagamento pagamento) {
        projetoc.domain.entity.PagamentoDebito debito = (projetoc.domain.entity.PagamentoDebito) pagamento;
        System.out.println("Processing Debit Card ending in: " + debito.getNumeroCartao());
        return true;
    }

    @Override
    public boolean seAplicaA(TipoPagamento tipoPagamento) {
        return TipoPagamento.DEBITO.equals(tipoPagamento);
    }
}
