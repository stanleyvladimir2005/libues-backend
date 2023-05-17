package sv.edu.ues.libues.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProviderDTO {

    @EqualsAndHashCode.Include
    private Long idProvider;

    @NotNull
    @Size(min = 3, max =100)
    private String providerName;

    @NotNull
    @Size(min = 3, max = 50)
    private String addressProvider;

    @NotNull
    @Size(min = 8, max = 8)
    private String providerPhone;

    @NotNull
    @Size(min = 3, max = 35)
    private String providerContact1;

    @NotNull
    @Size(min = 3, max = 35)
    private String providerContact2;

    @NotNull
    private boolean status;
}