package mk.finki.ukim.mk.laba.model.exceptions;

import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisTypeAnnos;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Invalid user credentials exception");
    }
}
