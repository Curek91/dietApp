package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.MealService;
import eu.tcitsolutions.dietApp.core.diet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;
    private DTOMappingService dtoMappingService;

    public MealServiceImpl(MealRepository mealRepository, DTOMappingService dtoMappingService){
        this.mealRepository = mealRepository;
        this.dtoMappingService = dtoMappingService;
    }

    @Override
    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal getMeal(Long id) {
        return mealRepository.findById(id).get();
    }

    @Override
    public void saveMeal(Meal meal) {
        mealRepository.save(meal);
    }

    @Override
    public void removeMeal(Long id) {
        mealRepository.deleteById(id);
    }

}
