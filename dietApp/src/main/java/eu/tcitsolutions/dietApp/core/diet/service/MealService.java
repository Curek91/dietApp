package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;

import java.util.List;

public interface MealService {
    public List<Meal> getMeals();

    public Meal getMeal(Long id);

/*    public Meal saveMeal(MealDTO source);*/

    public void removeMeal(Long id);

/*    public void updateMeal(Long id, MealDTO source);*/

/*    void init();*/

}
