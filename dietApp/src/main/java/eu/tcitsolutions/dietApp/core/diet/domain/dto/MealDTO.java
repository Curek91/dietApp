package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MealDTO {

    private Set<ProductOnDietDTO> products;
    private int mealNo;
    private String suplements;

}
