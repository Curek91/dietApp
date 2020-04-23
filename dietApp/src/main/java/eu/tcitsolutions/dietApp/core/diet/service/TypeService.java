package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;

import java.util.List;

public interface TypeService {
    public List<Type> getTypes();

    public Type getType(Long id);

}
