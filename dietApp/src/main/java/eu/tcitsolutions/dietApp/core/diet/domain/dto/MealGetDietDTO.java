package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MealGetDietDTO {

    private Set<ProductGetDietDTO> products;
    private int mealNo;
    private String suplements;

}
