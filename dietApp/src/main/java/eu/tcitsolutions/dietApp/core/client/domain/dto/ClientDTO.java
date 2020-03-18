package eu.tcitsolutions.dietApp.core.client.domain.dto;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Builder
public class ClientDTO {

    private Long clientNo;
    private String firstname;
    private String lastname;
    private Integer age;
    private Float  weight;
    private Integer height;
    private String email;
    private String telephone;
    private Integer biceps;
    private Integer chest;
    private Integer waist;
    private Integer thigh;
    private LocalDateTime date;

    public ClientDTO(Long clientNo, String firstname, String lastname, Integer age, Float weight, Integer height, String email, String telephone, Integer biceps, Integer chest, Integer waist, Integer thigh, LocalDateTime date) {
        this.clientNo = clientNo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.telephone = telephone;
        this.biceps = biceps;
        this.chest = chest;
        this.waist = waist;
        this.thigh = thigh;
        this.date = date;
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

    public Integer getBiceps() {
        return biceps;
    }

    public void setBiceps(Integer biceps) {
        this.biceps = biceps;
    }

    public Integer getChest() {
        return chest;
    }

    public void setChest(Integer chest) {
        this.chest = chest;
    }

    public Integer getWaist() {
        return waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getThigh() {
        return thigh;
    }

    public void setThigh(Integer thigh) {
        this.thigh = thigh;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getClientNo() {
        return clientNo;
    }

    public void setClientNo(Long clientNo) {
        this.clientNo = clientNo;
    }
}
