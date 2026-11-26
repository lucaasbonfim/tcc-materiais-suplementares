package projetoc.controller;

import projetoc.dto.ClienteResponseDTO;
import projetoc.dto.ClienteUpdateDTO;
import projetoc.security.MainUser;
import projetoc.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ClienteResponseDTO> getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MainUser mainUser = (MainUser) auth.getPrincipal();
        return ResponseEntity.ok(clienteService.getMe(mainUser.getUser().getId()));
    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ClienteResponseDTO> updateMe(@RequestBody ClienteUpdateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MainUser mainUser = (MainUser) auth.getPrincipal();
        return ResponseEntity.ok(clienteService.updateMe(mainUser.getUser().getId(), dto));
    }
}
