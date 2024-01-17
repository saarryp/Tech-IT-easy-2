package nl.novi.techiteasyopnieuw.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}
