package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface ProductService {
    public List<Product> getProducts();

    public Product getProduct(Long id);

    public Product saveProduct(ProductDTO source);

    public void removeProduct(Long id);

    public void updateProduct(Long id, ProductDTO source);

    //Image upload functions
    void store(MultipartFile file, Long id);

    public Resource getImage(Long id);

    void init();

}
