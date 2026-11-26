package projetoc.controller;

import projetoc.dto.ModalRequestDTO;
import projetoc.dto.ModalResponseDTO;
import projetoc.response.ApiResponse;
import projetoc.service.ModalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/modais")
public class ModalController {

    @Autowired
    private ModalService modalService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(projetoc.security.RoleConstants).PERM_MODAL_MANAGE)")
    public ResponseEntity<ApiResponse<ModalResponseDTO>> criar(@RequestBody @Valid ModalRequestDTO dto) {
        return ApiResponse.created("Modal cadastrado com sucesso", modalService.criar(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_MODAL_MANAGE, T(projetoc.security.RoleConstants).PERM_MODAL_READ)")
    public ResponseEntity<ApiResponse<List<ModalResponseDTO>>> listarTodos() {
        return ApiResponse.success("Modais listados com sucesso", modalService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_MODAL_MANAGE, T(projetoc.security.RoleConstants).PERM_MODAL_READ)")
    public ResponseEntity<ApiResponse<ModalResponseDTO>> buscarPorId(@PathVariable Long id) {
        return ApiResponse.success("Modal encontrado com sucesso", modalService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(T(projetoc.security.RoleConstants).PERM_MODAL_MANAGE, T(projetoc.security.RoleConstants).PERM_MODAL_MAINTENANCE)")
    public ResponseEntity<ApiResponse<ModalResponseDTO>> atualizar(@PathVariable Long id, @RequestBody @Valid ModalRequestDTO dto) {
        return ApiResponse.success("Modal atualizado com sucesso", modalService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(T(projetoc.security.RoleConstants).PERM_MODAL_MANAGE)")
    public ResponseEntity<ApiResponse<Void>> excluir(@PathVariable Long id) {
        modalService.excluir(id);
        return ApiResponse.success("Modal excluído com sucesso", null);
    }
}
