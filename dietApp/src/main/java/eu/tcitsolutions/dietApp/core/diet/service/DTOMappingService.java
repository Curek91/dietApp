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
    public DietDTO createDTO(Diet source);

    public MealDTO createDTO(Meal source);

    public ProductDTO createDTO(Product source);

    public TypeDTO createDTO(Type source);


    //create Entities
    public Diet createEntity(DietDTO source);
    public Diet createEntity(Long id, DietDTO source);

    public Meal createEntity(MealDTO source);
    public Meal createEntity(Long id, MealDTO source);

    public Product createEntity(ProductDTO source);
    public Product createEntity(Long id, ProductDTO source);

    public Type createEntity(TypeDTO source);
    public Type createEntity(Long id, TypeDTO source);

}
