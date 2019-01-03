package eu.tcitsolutions.dietApp.core.service.units;

import eu.tcitsolutions.dietApp.core.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    DTOMappingService dtoMappingService;

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    @Override
    public void saveProduct(ProductDTO source) {
        productRepository.save(dtoMappingService.createEntity(source));
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public void updateProduct(Long id, ProductDTO source) {
        productRepository.update(dtoMappingService.createEntity(id, source));
    }
}
