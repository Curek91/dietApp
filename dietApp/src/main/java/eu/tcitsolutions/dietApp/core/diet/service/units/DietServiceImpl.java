package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import eu.tcitsolutions.dietApp.core.diet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    DTOMappingService dtoMappingService;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public List<Diet> getDiets(Long clientId) {
        return dietRepository.getDiets(clientId);
    }

    @Override
    public DietDTO getDiet(Long id) {


        DietDTO dietDTO = dtoMappingService.createDTO(dietRepository.getDiet(id));
        return dietDTO;
    }

    @Override
    public Diet saveDiet(DietDTO source) {
       return dietRepository.save(dtoMappingService.createEntity(source));
    }

    @Override
    public void removeDiet(Long id) {
        dietRepository.delete(id);
    }

    @Override
    public void updateDiet(Long id, DietDTO source) {
        dietRepository.update(dtoMappingService.createEntity(id ,source));
    }
}
