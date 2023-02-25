package sv.edu.ues.libues.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuthorDTO {

    @EqualsAndHashCode.Include
    private Long idAuthor;

    @NotNull
    @Size(min = 3, max = 60)
    private String authorName;

    @NotNull
    private boolean status;
}
