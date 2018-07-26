package eu.tcitsolutions.dietApp.core.domain.dto;

import eu.tcitsolutions.dietApp.core.domain.entity.Type;
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
    private Long carb;
    private Long fat;
    private Long kcal;

}
