package proquartiano.it.proquartianobe.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsResponseWithListDTO(String message, LocalDateTime timestamp, List<String> errorsList) {
}
