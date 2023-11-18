package proquartiano.it.proquartianobe.payload.articles;

import jakarta.validation.constraints.NotEmpty;
import proquartiano.it.proquartianobe.entities.Admin;
import proquartiano.it.proquartianobe.entities.Category;
import proquartiano.it.proquartianobe.entities.Tag;

import java.util.List;
import java.util.UUID;

public record NewArticleDTO(
        UUID authorId,
        @NotEmpty(message = "Il contenuto non può essere vuoto.") String content,
        @NotEmpty(message = "Il titolo non può essere vuoto.") String title,
        @NotEmpty(message = "Scegliere almeno una categoria.") List<UUID> categoryIds,
        List<UUID> tagIds) {

}
