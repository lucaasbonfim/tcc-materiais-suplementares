package projetoc.service;

import projetoc.domain.entity.Pagamento;
import projetoc.domain.entity.PagamentoCredito;
import projetoc.domain.entity.PagamentoDebito;
import projetoc.domain.entity.Reserva;
import projetoc.domain.enums.StatusReserva;
import projetoc.domain.enums.TipoPagamento;
import projetoc.domain.repository.PagamentoRepository;
import projetoc.domain.repository.ReservaRepository;
import projetoc.dto.ReservaResponseDTO;
import projetoc.dto.VendaOnlineRequestDTO;
import projetoc.dto.VendaOnlineResponseDTO;
import projetoc.exception.BusinessException;
import projetoc.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendaOnlineService {

    private final ReservaService reservaService;
    private final ReservaRepository reservaRepository;
    private final ProcessadorPagamento processadorPagamento;
    private final PagamentoRepository pagamentoRepository;

    @Transactional
    public VendaOnlineResponseDTO solicitarVenda(VendaOnlineRequestDTO dto) {
        // 1. Criar a reserva inicialmente (fica com status PENDENTE internamente)
        ReservaResponseDTO reservaResponse = reservaService.create(dto.getReserva());
        
        Reserva reserva = reservaRepository.findById(reservaResponse.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", reservaResponse.getId()));

        // 2. Criar a entidade de pagamento adequada com base no tipo
        Pagamento pagamento = instanciarPagamento(dto);
        pagamento.setTipo(dto.getTipoPagamento());

        // 3. Processar o pagamento e atualizar status para AGUARDANDO_APROVACAO
        processadorPagamento.processarERegistrarPagamento(reserva, pagamento, StatusReserva.AGUARDANDO_APROVACAO);

        // 4. Salvar as entidades no banco
        pagamento = pagamentoRepository.save(pagamento);
        reserva = reservaRepository.save(reserva);

        return new VendaOnlineResponseDTO(
                reserva.getId(),
                reserva.getCodigo(),
                reserva.getStatus(),
                "Venda online solicitada. Aguardando aprovação gerencial."
        );
    }

    private Pagamento instanciarPagamento(VendaOnlineRequestDTO dto) {
        if (dto.getTipoPagamento() == TipoPagamento.CREDITO) {
            PagamentoCredito credito = new PagamentoCredito();
            credito.setNumeroCartao(dto.getNumeroCartao());
            credito.setParcelas(dto.getParcelas());
            return credito;
        } else if (dto.getTipoPagamento() == TipoPagamento.DEBITO) {
            PagamentoDebito debito = new PagamentoDebito();
            debito.setNumeroCartao(dto.getNumeroCartao());
            return debito;
        } else {
            throw new BusinessException("Apenas pagamentos por CRÉDITO ou DÉBITO são aceitos em vendas online.");
        }
    }
}
