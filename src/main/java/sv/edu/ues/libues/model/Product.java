package sv.edu.ues.libues.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idProduct;

    @ManyToOne
    @JoinColumn(name="id_area", nullable=false, foreignKey = @ForeignKey(name = "FK_PRODUCT_AREA"))
    private Area area;

    @ManyToOne
    @JoinColumn(name="id_provider", nullable=false, foreignKey = @ForeignKey(name = "FK_PRODUCT_PROVIDER"))
    private Provider provider;

    @ManyToOne
    @JoinColumn(name="id_editorial", nullable=false,  foreignKey = @ForeignKey(name = "FK_PRODUCT_EDITORIAL"))
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name="id_product_type", nullable=false,  foreignKey = @ForeignKey(name = "FK_PRODUCT_PRODUCT_TYPE"))
    private ProductType productType;

    @NotEmpty
    @Column(name="name_product", length = 50, nullable=false)
    private String nameProduct;

    @Column(name="correlative")
    private int correlative;

    @Column(name="existence")
    private int existence;

    @Column(name="hall")
    private int hall;

    @Column(name="minimum_unit")
    private int minimumUnit;

    @NotEmpty
    @Column(name="measure_unit", nullable=false)
    private int measureUnit;

    @NotEmpty
    @Column(name="country", nullable=false)
    private String country;

    @Column(name="isbn", nullable=false)
    private String isbn;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name="creation_date", nullable=false)
    private LocalDate creationDate;

    @Column(name="price")
    private Double price;

    @Column(name="unit_cost")
    private Double unitCost;

    @NotEmpty
    @Column(name="specific_expenses", nullable=false, length = 20)
    private String specificExpenses;

    @NotEmpty
    @Column(name="consignation", nullable=false, length = 10)
    private String consignation;

    @NotEmpty
    @Column(name="previous_provider")
    private int previousProvider;

    @Column(name="marked")
    private int marked;

    @Column(name="quantity_label")
    private int quantityLabel ;
}