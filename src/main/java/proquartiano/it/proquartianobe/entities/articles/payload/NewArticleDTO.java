package proquartiano.it.proquartianobe.entities.articles.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

public record NewArticleDTO(
//        UUID authorId,
        @NotBlank(message = "Il contenuto non può essere vuoto.") String content,
        @NotBlank(message = "Il titolo non può essere vuoto.") String title,
        List<String> categories,
//        @NotEmpty(message = "Scegliere almeno una categoria.") List<String> categoryIds,
        List<String> tags
//        MultipartFile img
//        String pdf
) {

}
