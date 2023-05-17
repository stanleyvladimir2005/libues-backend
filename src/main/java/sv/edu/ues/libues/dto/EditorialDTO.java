package sv.edu.ues.libues.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EditorialDTO {

    @EqualsAndHashCode.Include
    private Long idEditorial;

    @NotNull
    @Size(min = 4, max = 4)
    private String specificCode;

    @NotNull
    @Size(min = 3, max = 100)
    private String editorialName;

    @NotNull
    private boolean status;
}