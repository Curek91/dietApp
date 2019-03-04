package eu.tcitsolutions.dietApp.core.diet.service;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();

    public Product getProduct(Long id);

    public void saveProduct(ProductDTO source);

    public void removeProduct(Long id);

    public void updateProduct(Long id, ProductDTO source);
}
