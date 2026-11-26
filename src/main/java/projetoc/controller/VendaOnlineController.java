package projetoc.controller;

import projetoc.dto.VendaOnlineRequestDTO;
import projetoc.dto.VendaOnlineResponseDTO;
import projetoc.service.VendaOnlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas-online")
@RequiredArgsConstructor
public class VendaOnlineController {

    private final VendaOnlineService vendaOnlineService;

    @PostMapping("/solicitar")
    public ResponseEntity<VendaOnlineResponseDTO> solicitarVendaOnline(@Valid @RequestBody VendaOnlineRequestDTO requestDTO) {
        VendaOnlineResponseDTO response = vendaOnlineService.solicitarVenda(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
