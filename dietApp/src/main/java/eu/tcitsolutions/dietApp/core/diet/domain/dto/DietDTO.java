package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
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
    private Long clientId;

}
