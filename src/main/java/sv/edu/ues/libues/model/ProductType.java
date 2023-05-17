package sv.edu.ues.libues.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productType")
@SQLDelete(sql = "UPDATE productType SET status = false WHERE id_product_type = ?")
@Where(clause = "status = true")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductType;

    @Size(min = 3, max = 20, message = "{product.type.size}")
    @Column(name="product_type", nullable=false)
    private String productType;

    @Column(name="status", nullable=false)
    private boolean status;
}