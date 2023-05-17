package sv.edu.ues.libues.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AreaDTO {

    @EqualsAndHashCode.Include
    private Long idArea;

    @NotNull
    @Size(min = 3, max = 60)
    private String areaName;

    @NotNull
    private boolean status;
}