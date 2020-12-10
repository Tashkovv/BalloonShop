package mk.finki.ukim.mk.laba.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(Long manufacturerId) {
        super(String.format("Manufacturer with id %d was not found", manufacturerId));
    }
}
