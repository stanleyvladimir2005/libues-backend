package sv.edu.ues.libues.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class ProductAuthorPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;
}