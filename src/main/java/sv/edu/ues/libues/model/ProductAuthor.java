package sv.edu.ues.libues.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(ProductAuthorPK.class)
public class ProductAuthor {

    @Id
    private Product product;

    @Id
    private Author author;
}