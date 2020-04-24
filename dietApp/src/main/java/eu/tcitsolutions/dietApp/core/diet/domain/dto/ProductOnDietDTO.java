package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductOnDietDTO {

    private Long id;
    private int weight;

}
