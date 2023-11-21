package proquartiano.it.proquartianobe.entities.admins.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewAdminDTO(
        @NotBlank(message = "L'username è un campo obbligatorio.")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String username,
        @NotBlank(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotBlank(message = "La password è un campo obbligatorio!")
        String password) {
}
