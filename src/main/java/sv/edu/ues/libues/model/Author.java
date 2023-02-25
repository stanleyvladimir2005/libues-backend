package sv.edu.ues.libues.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="author")
@SQLDelete(sql = "UPDATE author SET status = false WHERE id_author = ?")
@Where(clause = "status = true")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthor;

    @Size(min = 3, max = 60, message = "{autor.nombre.size}")
    @Column(name = "author_name", length = 60)
    private String authorName;

    @NotNull
    @Column(name = "status")
    private boolean status;
}