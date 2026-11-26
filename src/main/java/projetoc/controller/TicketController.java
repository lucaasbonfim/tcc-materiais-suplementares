package projetoc.controller;

import projetoc.dto.TicketResponseDTO;
import projetoc.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{numero}")
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_TICKET_EMIT, T(projetoc.security.RoleConstants).PERM_RESERVA_READ, T(projetoc.security.RoleConstants).PERM_RESERVA_SELF_MANAGE, T(projetoc.security.RoleConstants).PERM_RESERVA_MANAGE_ALL)")
    public ResponseEntity<TicketResponseDTO> consultarTicket(@PathVariable String numero) {
        return ResponseEntity.ok(ticketService.findByNumero(numero));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'FUNCIONARIO')")
    public ResponseEntity<java.util.List<TicketResponseDTO>> listarTodos() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/reserva/{reservaId}")
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_READ, T(projetoc.security.RoleConstants).PERM_RESERVA_SELF_MANAGE, T(projetoc.security.RoleConstants).PERM_RESERVA_MANAGE_ALL)")
    public ResponseEntity<TicketResponseDTO> consultarPorReserva(@PathVariable Long reservaId) {
        return ResponseEntity.ok(ticketService.findByReservaId(reservaId));
    }
}
