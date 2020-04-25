package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.*;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.*;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Lazy
@Service
@Transactional
public class DTOMappingServiceImpl implements DTOMappingService, ApplicationContextAware {


    private TypeRepository typeRepository;
    private ClientRepository clientRepository;
    private MealRepository mealRepository;
    private DTOClientMappingService dtoClientMappingService;
    private ApplicationContext applicationContext;
    private ProductRepository productRepository;

    public DTOMappingServiceImpl(TypeRepository typeRepository, ClientRepository clientRepository, MealRepository mealRepository, ProductRepository productRepository, DTOClientMappingService dtoClientMappingService, ApplicationContext applicationContext){
        this.typeRepository = typeRepository;
        this.clientRepository = clientRepository;
        this.mealRepository = mealRepository;
        this.productRepository = productRepository;
        this.dtoClientMappingService = dtoClientMappingService;
        this.applicationContext = applicationContext;
    }

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void handleDependencies() {
        this.typeRepository = applicationContext.getBean(TypeRepository.class);
    }

    /////////////////////////
    //////CREATE DTO/////////
    /////////////////////////


    @Override
    public DietGetDietDTO createDTO(Diet source) {
        DietGetDietDTO dietGetDietDTO = new DietGetDietDTO(source.getId(), new HashSet<MealGetDietDTO>());
        MealGetDietDTO mealGetDietDTO;


        for(Meal meal : source.getMeals()){
            mealGetDietDTO = new MealGetDietDTO();
            mealGetDietDTO.setMealNo(meal.getMealNo());
            mealGetDietDTO.setSuplements(meal.getSuplements());
            Set<ProductGetDietDTO> products = new HashSet<>();
            for(MealProduct mp: meal.getMealProducts()){
                products.add(createGetDietDTO(mp));
            }
            mealGetDietDTO.setProducts(products);
            dietGetDietDTO.getMeals().add(mealGetDietDTO);
        }

        return dietGetDietDTO;
    }

    @Override
    public TypeDTO createDTO(Type source) {
        return new TypeDTO(source.getId(), source.getName());
    }

    @Override
    public ProductDTO createDTO(Product source) {
        return new ProductDTO(source.getId(), source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), createDTO(source.getType()));
    }

    @Override
    public ProductGetDietDTO createGetDietDTO(MealProduct source) {
        return new ProductGetDietDTO(source.getProduct().getId(),
                source.getProduct().getName(),
                source.getProduct().getProtein(),
                source.getProduct().getCarbs(),
                source.getProduct().getFat(),
                source.getProduct().getKcal(),
                createDTO(source.getProduct().getType()),
                source.getWeight());
    }


    /////////////////////////
    //////CREATE ENTITIES////
    /////////////////////////

    @Override
    public Diet createEntity(DietDTO source) {
        Diet diet = new Diet(source.getMeals().stream().map(mealDTO -> createEntity(mealDTO)).collect(Collectors.toSet()), null);
        return diet;
    }

    @Override
    public Meal createEntity(MealDTO source) {
        Meal meal = new Meal();
        meal.setMealNo(source.getMealNo());
        meal.setSuplements(source.getSuplements());
        source.getProducts().stream().forEach(p -> meal.addProduct(productRepository.findById(p.getId()).get(), p.getWeight()));
        return meal;
    }

    @Override
    public Type createEntity(TypeDTO source){
    return new Type(source.getId(), source.getName());
    }

    @Override
    public Product createEntity(ProductDTO source){
        return new Product(source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), createEntity(source.getType()));
    }
}
