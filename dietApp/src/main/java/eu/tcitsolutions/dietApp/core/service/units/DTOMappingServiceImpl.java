package eu.tcitsolutions.dietApp.core.service.units;

import eu.tcitsolutions.dietApp.core.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.service.DTOMappingService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import sun.net.ftp.FtpDirEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Lazy
@Service
@Transactional
public class DTOMappingServiceImpl implements DTOMappingService, ApplicationContextAware {

    private ProductRepository productRepository;
    private TypeRepository typeRepository;

    private ApplicationContext applicationContext;

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void handleDependencies() {
        this.productRepository = applicationContext.getBean(ProductRepository.class);
        this.typeRepository = applicationContext.getBean(TypeRepository.class);
    }

    @Override
    public MealDTO createDTO(Meal source) {
        return new MealDTO(source.getProducts().stream().map(product -> createDTO(product)).collect(Collectors.toSet()));
    }

    @Override
    public DietDTO createDTO(Diet source) {
        return new DietDTO(source.getMeals().stream().map(meal -> createDTO(meal)).collect(Collectors.toSet()));
    }

    @Override
    public ProductDTO createDTO(Product source) {
        return new ProductDTO(createDTO(source.getType()), source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), source.getImageName());
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
        return new Product(source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), createEntity(source.getType()), source.getImageName());
    }

    @Override
    public Product createEntity(Long id, ProductDTO source){
        return new Product(id, source.getName(), source.getProtein(), source.getCarbs(), source.getFat(), source.getKcal(), createEntity(source.getType()), source.getImageName());
    }
}
