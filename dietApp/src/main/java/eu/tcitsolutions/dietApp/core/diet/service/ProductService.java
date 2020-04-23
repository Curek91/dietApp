package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveProduct(Product source);

    void removeProduct(Long id);

    void updateProduct(Long id, Product source);

    //Image upload functions
    void store(MultipartFile file, Long id);

    Resource getImage(Long id);

    void init();

}
