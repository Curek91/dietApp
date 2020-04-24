package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();

    Page<ProductDTO> getProducts(Pageable page);

    ProductDTO getProduct(Long id);

    Product saveProduct(ProductDTO source);

    void removeProduct(Long id);

    Product updateProduct(Long id, ProductDTO source);

    //Image upload functions
    void store(MultipartFile file, Long id);

    Resource getImage(Long id);

    void init();

}
