package projetoc.controller;

import projetoc.dto.ReservaRequestDTO;
import projetoc.dto.ReservaResponseDTO;
import projetoc.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_CREATE, T(projetoc.security.RoleConstants).PERM_RESERVA_SELF_MANAGE, T(projetoc.security.RoleConstants).PERM_RESERVA_MANAGE_ALL)")
    public ResponseEntity<ReservaResponseDTO> create(@Valid @RequestBody ReservaRequestDTO dto) {
        ReservaResponseDTO created = reservaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_READ, T(projetoc.security.RoleConstants).PERM_RESERVA_ONLINE_READ, T(projetoc.security.RoleConstants).PERM_RESERVA_SELF_MANAGE, T(projetoc.security.RoleConstants).PERM_RESERVA_MANAGE_ALL)")
    public ResponseEntity<List<ReservaResponseDTO>> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/minhas")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ReservaResponseDTO>> findMinhasReservas() {
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        projetoc.security.MainUser mainUser = (projetoc.security.MainUser) auth.getPrincipal();
        return ResponseEntity.ok(reservaService.findMinhasReservas(mainUser.getUser().getId()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_READ, T(projetoc.security.RoleConstants).PERM_RESERVA_ONLINE_READ, T(projetoc.security.RoleConstants).PERM_RESERVA_SELF_MANAGE, T(projetoc.security.RoleConstants).PERM_RESERVA_MANAGE_ALL)")
    public ResponseEntity<ReservaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    @PatchMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_RESERVA_SELF_MANAGE, T(projetoc.security.RoleConstants).PERM_RESERVA_MANAGE_ALL)")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservaService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
