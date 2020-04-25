package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> getTypes();

    Type getType(Long id);

}
