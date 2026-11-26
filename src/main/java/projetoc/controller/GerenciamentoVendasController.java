package projetoc.controller;

import projetoc.domain.entity.Ticket;
import projetoc.response.ApiResponse;
import projetoc.service.GerenciamentoVendasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/gerenciamento-vendas")
@RequiredArgsConstructor
public class GerenciamentoVendasController {

    private final GerenciamentoVendasService gerenciamentoVendasService;

    @PutMapping("/{reservaId}/aprovar")
    @PreAuthorize("hasAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_ONLINE_APPROVE)")
    public ResponseEntity<ApiResponse<Map<String, String>>> aprovarVenda(@PathVariable Long reservaId) {
        Ticket ticket = gerenciamentoVendasService.aprovarVendaOnline(reservaId);
        
        return ApiResponse.success("Venda aprovada com sucesso. Ticket emitido.", 
                Map.of(
                    "ticketNumero", ticket.getNumero(),
                    "assento", ticket.getAssento(),
                    "reservaCodigo", ticket.getReserva().getCodigo()
                ));
    }

    @PutMapping("/{reservaId}/rejeitar")
    @PreAuthorize("hasAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_ONLINE_REJECT)")
    public ResponseEntity<ApiResponse<Void>> rejeitarVenda(@PathVariable Long reservaId) {
        gerenciamentoVendasService.rejeitarVendaOnline(reservaId);
        return ApiResponse.success("Venda rejeitada com sucesso.", null);
    }
}
