package eu.tcitsolutions.dietApp.core.domain.dto;

public class ProductDTO {

    private Long id;
    private String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
