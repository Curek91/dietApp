package eu.tcitsolutions.dietApp.core.diet.domain.dto;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DietDTO {

    private Long id;
    private Set<MealDTO> meals;
    private Double kcal;
    private String author;
    private LocalDateTime date;

}
