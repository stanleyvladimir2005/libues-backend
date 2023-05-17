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
@Table(name="area")
@SQLDelete(sql = "UPDATE area SET status = false WHERE id_area = ?")
@Where(clause = "status = true")
public class Area {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idArea;

    @Size(min = 3, max = 50, message = "{area.name.size}")
    @Column(name="area_name", nullable=false)
    private String areaName;

    @Column(name="status", nullable=false)
    private boolean status;
}