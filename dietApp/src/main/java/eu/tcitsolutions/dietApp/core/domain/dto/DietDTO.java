package eu.tcitsolutions.dietApp.core.domain.dto;

import eu.tcitsolutions.dietApp.core.domain.entity.Meal;
import java.util.Set;

public class DietDTO {

    private Long id;
    private Set<Meal> meals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}
