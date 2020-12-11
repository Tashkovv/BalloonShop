package mk.finki.ukim.mk.laba.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(String name) {
        super(String.format("Manufacturer with name %s was not found", name));
    }
}
