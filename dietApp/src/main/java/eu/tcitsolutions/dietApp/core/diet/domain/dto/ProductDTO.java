package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private TypeDTO type;
    private String name;
    private Long protein;
    private Long carbs;
    private Long fat;
    private Long kcal;
    private int weight;

    public ProductDTO(TypeDTO type, String name, Long protein, Long carbs, Long fat, Long kcal){
        this.type = type;
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.kcal = kcal;
    }

}
