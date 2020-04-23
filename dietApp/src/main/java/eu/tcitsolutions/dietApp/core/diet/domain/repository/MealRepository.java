package eu.tcitsolutions.dietApp.core.diet.domain.repository;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MealRepository {
    List<Meal> findAll();

    Page<Meal> findAll(Pageable page);

    Optional<Meal> findById(Long id);

    Meal save(Meal client);

    void deleteById(Long id);
}
