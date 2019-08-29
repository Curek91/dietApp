package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
public class MealDTO {

    private Set<ProductDTO> products;
    private int mealNo;
    private int kcal;
    private String suplements;

    public MealDTO() {
    }

    public MealDTO(Set<ProductDTO> products, int mealNo, int kcal, String suplements) {
        this.products = products;
        this.mealNo = mealNo;
        this.kcal = kcal;
        this.suplements = suplements;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    public int getMealNo() {
        return mealNo;
    }

    public void setMealNo(int mealNo) {
        this.mealNo = mealNo;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getSuplements() {
        return suplements;
    }

    public void setSuplements(String suplements) {
        this.suplements = suplements;
    }
}
