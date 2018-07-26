package eu.tcitsolutions.dietApp.core.domain.dto;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {

    private Set<ProductDTO> products;

}
