package projetoa.infra.security.exception;

public class CursoNotFoundException extends RuntimeException {
    public CursoNotFoundException(String message) {
        super(message);
    }
}
