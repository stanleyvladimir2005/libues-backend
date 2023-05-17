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
@Table(name="editorial")
@SQLDelete(sql = "UPDATE editorial SET status = false WHERE id_editorial = ?")
@Where(clause = "status = true")
public class Editorial {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEditorial;

    @Size(min = 4, max = 4, message = "{specific.code.size}")
    @Column(name="specific_code")
    private String specificCode;

    @Size(min = 3, max = 100, message = "{editorial.name.size}")
    @Column(name="editorial_name")
    private String editorialName;

    @Column(name="status")
    private boolean status;
}