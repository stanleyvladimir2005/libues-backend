package sv.edu.ues.libues.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="author")
@SQLDelete(sql = "UPDATE author SET status = false WHERE id_author = ?")
@SQLRestriction("status = true")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idAuthor;

    @Size(min = 3, max = 60, message = "{autor.name.size}")
    @Column(name = "author_name", length = 60)
    private String authorName;

    @Column(name = "status")
    private boolean status;
}
