package proquartiano.it.proquartianobe.articles.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record NewArticleDTO(
        UUID authorId,
        @NotBlank(message = "Il contenuto non può essere vuoto.") String content,
        @NotBlank(message = "Il titolo non può essere vuoto.") String title,
        @NotEmpty(message = "Scegliere almeno una categoria.") List<UUID> categoryIds,
        List<UUID> tagIds,
        String img
//        String pdf
) {

}
