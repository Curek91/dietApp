package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DietDTO {

    private Set<MealDTO> meals;
    private Double kcal;

}
