package eu.tcitsolutions.dietApp.core.diet.domain.repository;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;

import java.util.List;

public interface MealRepository {
    public List<Meal> getMeals();

    public Meal getMeal(Long id);

    public Meal save(Meal meal);

    public void delete(Meal meal);

    public void delete(Long id);

    public void update(Meal meal);
}
