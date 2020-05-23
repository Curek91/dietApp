package eu.tcitsolutions.dietApp.core.client.domain.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private String firstname;
    private String lastname;
    private Integer age;
    private Float  weight;
    private Integer height;
    private String email;
    private String telephone;
    private Integer biceps;
    private Integer chest;
    private Integer waist;
    private Integer thigh;
    private LocalDateTime date;
    private Long clientNo;

}
