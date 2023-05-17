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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="provider")
@SQLDelete(sql = "UPDATE provider SET status = false WHERE id_provider = ?")
@Where(clause = "status = true")
public class Provider {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProvider;

    @Size(min = 3, max = 100, message = "{provider.name.size}")
    @Column(name="provider_name")
    private String providerName;

    @Size(min = 3, max = 50, message = "{provider.address.name.size}")
    @Column(name="address_provider")
    private String addressProvider;

    @NotNull
    @Size(min = 8, max = 8, message = "{provider.phone.size}")
    @Column(name="provider_phone")
    private String providerPhone;

    @Size(min = 3, max = 35, message = "{provider.contact1.size}")
    @Column(name="provider_contact_1")
    private String providerContact1;

    @Size(min = 3, max = 35, message = "{provider.contact2.size}")
    @Column(name="provider_contact_2")
    private String providerContact2;

    @Column(name="status")
    private boolean status;
}