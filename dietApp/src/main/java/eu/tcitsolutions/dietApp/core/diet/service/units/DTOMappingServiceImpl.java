package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
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
import java.util.HashSet;
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

    public DTOMappingServiceImpl(TypeRepository typeRepository, ClientRepository clientRepository, MealRepository mealRepository, DTOClientMappingService dtoClientMappingService, ApplicationContext applicationContext){
        this.typeRepository = typeRepository;
        this.clientRepository = clientRepository;
        this.mealRepository = mealRepository;
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
    public DietDTO createDTO(Diet source){
        return new DietDTO(source.getMeals().stream().map(meal -> createDTO(meal)).collect(Collectors.toSet()), 0.0, source.getClient().getId());
    }

    @Override
    public MealDTO createDTO(Meal source){
        return new MealDTO(source.getMealProducts().stream().map(prod -> createDTO(prod.getProduct(), prod.getWeight(), prod.getId())).collect(Collectors.toSet()), source.getMealNo(), 0,source.getSuplements());
    }

    @Override
    public ProductDTO createDTO(Product source) {
        return new ProductDTO(createDTO(source.getType()), source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal());
    }

    @Override
    public ProductDTO createDTO(Product source, int weight, Long sortNo) {
        return new ProductDTO(source.getId(), createDTO(source.getType()), source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), weight, sortNo);
    }

    @Override
    public TypeDTO createDTO(Type source) {
        return new TypeDTO(null, source.getName());
    }


    /////////////////////////
    //////CREATE ENTITIES////
    /////////////////////////

    @Override
    public Diet createEntity(DietDTO source) {
        Diet diet = new Diet(source.getMeals().stream().map(mealDTO -> createEntity(mealDTO)).collect(Collectors.toSet()), clientRepository.findById(source.getClientNo()).get());
        return diet;
    }

    @Override
    public Diet createEntity(Long id, DietDTO source) {
        Diet diet = new Diet(id, source.getMeals().stream().map(mealDTO -> createEntity(mealDTO)).collect(Collectors.toSet()), clientRepository.findById(source.getClientNo()).get());
        return diet;
    }

    @Override
    public Meal createEntity(MealDTO source) {
        Meal meal = new Meal();
        meal.setMealNo(source.getMealNo());
        meal.setSuplements(source.getSuplements());
        source.getProducts().stream().forEach(p -> meal.addProduct(createEntity(p.getId(), p), p.getWeight()));
        return meal;
    }

    @Override
    public Meal createEntity(Long id, MealDTO source) {
        Meal meal = new Meal();
        meal.setId(id);
        meal.setMealNo(source.getMealNo());
        meal.setSuplements(source.getSuplements());
        source.getProducts().stream().forEach(p -> meal.addProduct(createEntity(p.getId(), p), p.getWeight()));
        return meal;
    }


    @Override
    public Product createEntity(ProductDTO source){
        return new Product(source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), createEntity(source.getType()));
    }

    @Override
    public Product createEntity(Long id, ProductDTO source){
        return new Product(id, source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), createEntity(source.getType()));
    }

    @Override
    public Type createEntity(Long id, TypeDTO source){
        return new Type(id, source.getName());
    }

    @Override
    public Type createEntity(TypeDTO source){
        String name = source.getName();
        if (source.getId() != null){
            name = typeRepository.findById(source.getId()).get().getName();
        }
        return new Type(source.getId(), name);
    }

}
