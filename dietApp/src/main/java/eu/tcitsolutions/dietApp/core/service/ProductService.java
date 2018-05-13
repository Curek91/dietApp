package eu.tcitsolutions.dietApp.core.service;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();

    public Product getProduct(Long id);

    public void saveProduct(Product source);
}
