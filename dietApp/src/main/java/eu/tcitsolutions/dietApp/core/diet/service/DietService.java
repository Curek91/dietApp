package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietGetDietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DietService {
    List<DietDTO> getDiets(Long clientNo);

    DietGetDietDTO getDiet(Long id);

    Diet saveDiet(Long clientNo, DietDTO source);

    void removeDiet(Long id);

    Diet updateDiet(Long id, DietDTO source);

}
