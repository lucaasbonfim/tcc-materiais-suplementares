package projetoa.infra.security.exception;

public class MateriaNotFoundException extends RuntimeException {
    public MateriaNotFoundException(String message) {
        super(message);
    }
}
