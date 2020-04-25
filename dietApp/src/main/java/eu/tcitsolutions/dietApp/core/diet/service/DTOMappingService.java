package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.*;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.*;

public interface DTOMappingService {

    //Create DTO's
    DietGetDietDTO createDTO(Diet source);

    ProductDTO createDTO(Product source);
    ProductGetDietDTO createGetDietDTO(MealProduct source);

    TypeDTO createDTO(Type source);


    //create Entities
    Diet createEntity(DietDTO source);

    Meal createEntity(MealDTO source);

    Product createEntity(ProductDTO source);

    Type createEntity(TypeDTO source);

}
