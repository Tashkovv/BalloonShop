package mk.finki.ukim.mk.laba.model.exceptions;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException() {
        super("Invalid attributes!");
    }
}
