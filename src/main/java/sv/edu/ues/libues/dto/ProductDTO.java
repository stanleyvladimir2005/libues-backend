package sv.edu.ues.libues.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDTO {

    @EqualsAndHashCode.Include
    private Long idProduct;

    @NotNull
    private AreaDTO area;

    @NotNull
    private ProviderDTO provider;

    @NotNull
    private EditorialDTO editorial;

    @NotNull
    private ProductTypeDTO productType;

    @NotNull
    private String nameProduct;

    private int correlative;

    private int existence;

    private int hall;

    private int minimumUnit;

    private int measureUnit;

    @NotNull
    private String country;

    @NotNull
    private String isbn;

    @NotNull
    private LocalDate creationDate;

    private Double price;

    private Double unitCost;

    @NotNull
    private String specificExpenses;

    @NotNull
    private String consignation;

    private int previousProvider;

    private int marked;

    private int quantityLabel ;
}