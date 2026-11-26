package projetoa.infra.security.exception;

public class CursoGradeNotFoundException extends RuntimeException {
    public CursoGradeNotFoundException(String message) {
        super(message);
    }
}
