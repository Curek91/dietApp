package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
public class DietDTO {

    private Set<MealDTO> meals;
    private Double kcal;
    private Long clientId;

    public DietDTO() {
    }

    public DietDTO(Set<MealDTO> meals, Double kcal, Long clientId) {
        this.meals = meals;
        this.kcal = kcal;
        this.clientId = clientId;
    }

    public DietDTO(Set<MealDTO> meals, Long clientId) {
        this.meals = meals;
        this.clientId = clientId;
    }

    public Set<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealDTO> meals) {
        this.meals = meals;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
