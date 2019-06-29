package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ProductRepository productRepository;
    private TypeRepository typeRepository;

    @Autowired
    private MealRepository mealRepository;

    private ApplicationContext applicationContext;

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void handleDependencies() {
        this.productRepository = applicationContext.getBean(ProductRepository.class);
        this.typeRepository = applicationContext.getBean(TypeRepository.class);
    }

/*    @Override
    public MealDTO createDTO(Meal source) {
        return new MealDTO(source.getProducts().stream().map(product -> createDTO(product.getProduct())).collect(Collectors.toSet()));
    }*/

/*
    @Override
    public DietDTO createDTO(Diet source) {
        return new DietDTO(source.getMeals().stream().map(meal -> createDTO(meal)).collect(Collectors.toSet()));
    }
*/

    @Override
    public ProductDTO createDTO(Product source) {
        return new ProductDTO(createDTO(source.getType()), source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal());
    }

    @Override
    public TypeDTO createDTO(Type source) {
        return new TypeDTO(null, source.getName());
    }

    @Override
    public Type createEntity(TypeDTO source){
        String name = source.getName();
        if (source.getId() != null){
            name = typeRepository.getType(source.getId()).getName();
        }
        return new Type(source.getId(), name);
    }

    @Override
    public Type createEntity(Long id, TypeDTO source){
        return new Type(id, source.getName());
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
    public Diet createEntity(Long id, DietDTO source) {
        return null;
    }

    @Override
    public Diet createEntity(DietDTO source) {
        Diet diet = new Diet();

        return new Diet();
    }

    @Override
    public Meal createEntity(MealDTO source) {
        return null;
    }

    @Override
    public Meal createEntity(Long id, MealDTO source) {
        return null;
    }

/*    @Override
    public Diet createEntity(Long id, DietDTO source){
        return new Diet(id, source.getMeals());
    }

    @Override
    public Meal createEntity(Long id, MealDTO source){
        return new Meal(id, source.getMealNo(), source.getProducts().stream().map(productDTO -> createEntity(productDTO)));
    }*/
}
