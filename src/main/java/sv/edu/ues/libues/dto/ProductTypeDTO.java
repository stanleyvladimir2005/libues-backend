package sv.edu.ues.libues.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductTypeDTO {

    @EqualsAndHashCode.Include
    private Long idProductType;

    @NotNull
    @Size(min = 3, max = 20)
    private String productType;

    @NotNull
    private boolean status;
}
