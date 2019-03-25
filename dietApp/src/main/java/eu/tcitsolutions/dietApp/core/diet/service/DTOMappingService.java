package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;

public interface DTOMappingService {

    //Create DTO's
    public TypeDTO createDTO(Type source);

    public ProductDTO createDTO(Product source);

/*    public MealDTO createDTO(Meal source);

    public DietDTO createDTO(Diet source);*/


    //create Entities
    public Type createEntity(TypeDTO source);
    public Type createEntity(Long id, TypeDTO source);

    public Product createEntity(ProductDTO source);
    public Product createEntity(Long id, ProductDTO source);
}