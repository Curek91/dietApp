package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public class ProductDTO {

    private Long id;
    private TypeDTO type;
    private String name;
    private Long protein;
    private Long carbs;
    private Long fat;
    private Long kcal;
    private int weight;
    private Long sortNo;

    public ProductDTO(TypeDTO type, String name, Long protein, Long carbs, Long fat, Long kcal, int weight){
        this.type = type;
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.kcal = kcal;
        this.weight = weight;
    }

    public ProductDTO(TypeDTO type, String name, Long protein, Long carbs, Long fat, Long kcal){
        this.type = type;
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.kcal = kcal;
    }

    public ProductDTO(Long id, TypeDTO type, String name, Long protein, Long carbs, Long fat, Long kcal, int weight, Long sortNo){
        this.id = id;
        this.type = type;
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.kcal = kcal;
        this.weight = weight;
        this.sortNo = sortNo;
    }

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDTO getType() {
        return type;
    }

    public void setType(TypeDTO type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProtein() {
        return protein;
    }

    public void setProtein(Long protein) {
        this.protein = protein;
    }

    public Long getCarbs() {
        return carbs;
    }

    public void setCarbs(Long carbs) {
        this.carbs = carbs;
    }

    public Long getFat() {
        return fat;
    }

    public void setFat(Long fat) {
        this.fat = fat;
    }

    public Long getKcal() {
        return kcal;
    }

    public void setKcal(Long kcal) {
        this.kcal = kcal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }
}
