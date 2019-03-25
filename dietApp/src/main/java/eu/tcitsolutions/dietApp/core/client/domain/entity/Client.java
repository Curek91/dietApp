package eu.tcitsolutions.dietApp.core.client.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Client extends BaseLogEntity implements Serializable {

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