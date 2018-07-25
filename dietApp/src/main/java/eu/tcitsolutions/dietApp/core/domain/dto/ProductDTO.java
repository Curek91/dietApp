package eu.tcitsolutions.dietApp.core.domain.dto;

import eu.tcitsolutions.dietApp.core.domain.entity.Type;

public class ProductDTO {

    private Long id;
    private Type type;
    private String name;
    private Long protein;
    private Long carb;
    private Long fat;
    private Long kcal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

    public Long getCarb() {
        return carb;
    }

    public void setCarb(Long carb) {
        this.carb = carb;
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
}
