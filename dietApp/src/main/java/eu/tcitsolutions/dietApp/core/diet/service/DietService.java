package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DietService {
    public List<Diet> getDiets();

    public Diet getDiet(Long id);

    public Diet saveDiet(DietDTO source);

    public void removeDiet(Long id);

/*
    public void updateDiet(Long id, DietDTO source);
*/

    /*    void init();*/

}
