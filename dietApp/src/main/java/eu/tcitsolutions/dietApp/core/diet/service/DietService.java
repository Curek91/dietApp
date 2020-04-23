package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DietService {
    List<Diet> getDiets(Long clientNo);

    DietDTO getDiet(Long id);

    Diet saveDiet(DietDTO source);

    void removeDiet(Long id);

    void updateDiet(Long id, Diet source);

}
