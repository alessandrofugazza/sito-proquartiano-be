package proquartiano.it.proquartianobe.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("No record for id " + id + ".");
    }
}
