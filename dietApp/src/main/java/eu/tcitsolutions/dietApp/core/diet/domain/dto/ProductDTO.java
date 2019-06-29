package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private TypeDTO type;
    private String name;
    private Long protein;
    private Long carbs;
    private Long fat;
    private Long kcal;

}
