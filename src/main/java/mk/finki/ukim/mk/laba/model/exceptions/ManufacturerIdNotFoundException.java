package mk.finki.ukim.mk.laba.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerIdNotFoundException extends RuntimeException {
    public ManufacturerIdNotFoundException(Long id) {
        super(String.format("Manufacturer with id %d was not found", id));
    }
}
