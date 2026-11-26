package projetob.webservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetob.services.ReservaService;
import projetob.webservices.requests.CalculoValorReservaRequest;
import projetob.webservices.responses.CalculoValorReservaResponse;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaPublicWebservice {
	private final ReservaService reservaService;

	public ReservaPublicWebservice(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@PostMapping("/calcular-valor")
	public ResponseEntity<CalculoValorReservaResponse> calculoValorReserva(
			@RequestBody CalculoValorReservaRequest request) {
		return ResponseEntity.ok(reservaService.calcularValorReserva(request));
	}
}
