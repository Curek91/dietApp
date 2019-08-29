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

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
