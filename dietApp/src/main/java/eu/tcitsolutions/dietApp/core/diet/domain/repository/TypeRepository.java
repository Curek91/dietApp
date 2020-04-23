package eu.tcitsolutions.dietApp.core.diet.domain.repository;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;

import java.util.List;
import java.util.Optional;

public interface TypeRepository {
    List<Type> findAll();

    Optional<Type> findById(Long id);
}
