package eu.tcitsolutions.dietApp.core.client.domain.dto;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
public class ClientDTO {

    private String firstname;
    private String lastname;
    private Integer age;
    private Float  weight;
    private Integer height;
    private String email;
    private String telephone;

    public ClientDTO(String firstname, String lastname, Integer age, Float weight, Integer height, String email, String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.telephone = telephone;
    }

    public ClientDTO() {
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
