package sv.edu.ues.libues.dto;

import lombok.Data;

@Data
public class ProductAuthorDTO {

    private ProductDTO product;
    private AuthorDTO author;
}