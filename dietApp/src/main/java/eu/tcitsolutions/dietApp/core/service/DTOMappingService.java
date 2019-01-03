package eu.tcitsolutions.dietApp.core.service;

import eu.tcitsolutions.dietApp.core.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.entity.Type;

public interface DTOMappingService {

    //Create DTO's
    public TypeDTO createDTO(Type source);

    public ProductDTO createDTO(Product source);

    public MealDTO createDTO(Meal source);

    public DietDTO createDTO(Diet source);


    //create Entities
    public Type createEntity(TypeDTO source);
    public Type createEntity(Long id, TypeDTO source);

    public Product createEntity(ProductDTO source);
    public Product createEntity(Long id, ProductDTO source);
}
