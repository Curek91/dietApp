package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DietGetDietDTO {

    private Long id;
    private Set<MealGetDietDTO> meals;
    private String createdBy;
    private LocalDateTime creationTime;

}
