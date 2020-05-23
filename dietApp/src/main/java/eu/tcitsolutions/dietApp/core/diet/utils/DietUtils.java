package eu.tcitsolutions.dietApp.core.diet.utils;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.MealProduct;

public class DietUtils {
    public static Double calculateKcal(Diet diet){
        Double kcal = 0.0;
        for(Meal meal : diet.getMeals()){
            for (MealProduct mp : meal.getMealProducts()){
                kcal += (mp.getProduct().getKcal() * mp.getWeight() / 100);
            }
        }
        return kcal;
    }
}
