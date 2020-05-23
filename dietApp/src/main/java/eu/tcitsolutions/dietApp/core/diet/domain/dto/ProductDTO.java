package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Long protein;
    private Long carbs;
    private Long fat;
    private Long kcal;
    private TypeDTO type;

}
