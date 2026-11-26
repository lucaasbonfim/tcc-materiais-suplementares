package projetoc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import projetoc.domain.entity.Pagamento;
import projetoc.domain.entity.Reserva;
import projetoc.domain.entity.Ticket;
import projetoc.domain.enums.StatusPagamento;
import projetoc.domain.enums.StatusReserva;
import projetoc.domain.enums.TipoPagamento;
import projetoc.domain.repository.TicketRepository;
import projetoc.strategy.PagamentoStrategy;

@Service
public class ProcessadorPagamento {

    private final List<PagamentoStrategy> estrategias;
    private final TicketRepository ticketRepository;

    public ProcessadorPagamento(List<PagamentoStrategy> estrategias, TicketRepository ticketRepository) {
        this.estrategias = estrategias;
        this.ticketRepository = ticketRepository;
    }

    public void processarERegistrarPagamento(Reserva reserva, Pagamento pagamento, StatusReserva statusSucesso) {
        // Convert entity string suffix to your matching TipoPagamento enum instance
        String suffix = pagamento.getClass().getSimpleName()
                                .replace("Pagamento", "")
                                .toUpperCase();
        TipoPagamento tipoMapping = TipoPagamento.valueOf(suffix);

        // 1. Automatic Strategy Selection
        PagamentoStrategy estrategiaSelecionada = estrategias.stream()
                .filter(e -> e.seAplicaA(tipoMapping))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No payment strategy found for type: " + tipoMapping));
        
        // 2. Execute Payment Transaction Logic
        boolean sucesso = estrategiaSelecionada.processar(pagamento);

        if (sucesso) {
            // 3. Registrar Pagamento (Link transaction meta to entity)
            pagamento.setStatus(StatusPagamento.APROVADO);
            pagamento.setReserva(reserva); // FK lives on Pagamento side

            // 4. Atualizar status da reserva
            reserva.setStatus(statusSucesso); // Updates your reservation state machine
            System.out.println("Reservation " + reserva.getId() + " successfully confirmed via Strategy! Status: " + statusSucesso);
        } else {
            pagamento.setStatus(StatusPagamento.RECUSADO);
            reserva.setStatus(StatusReserva.PENDENTE);
        }
    }

    // Método mantido para compatibilidade com códigos existentes
    public void processarERegistrarPagamento(Reserva reserva, Pagamento pagamento) {
        processarERegistrarPagamento(reserva, pagamento, StatusReserva.CONFIRMADA);
    }
}