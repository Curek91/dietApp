package eu.tcitsolutions.dietApp.core.client.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "client")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Client extends BaseLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq_generator")
    @SequenceGenerator(name="client_seq_generator", sequenceName = "client_seq", allocationSize=1)
    @NaturalId
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Float  weight;
    private Integer height;
    private String email;
    private String telephone;


    public Client(String firstname, String lastname, Integer age, Float weight, Integer height, String email, String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.telephone = telephone;
    }

    public Client(Long id, String firstname, String lastname, Integer age, Float weight, Integer height, String email, String telephone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.telephone = telephone;
    }

}
