package proquartiano.it.proquartianobe.exceptions;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
