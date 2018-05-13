package eu.tcitsolutions.dietApp.core.domain.repository;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> getProducts();

    public Product getProduct(Long id);

    public Product load(Long id);

    public void save(Product product);

    public void delete(Product product);

    public void delete(Long id);

    public void update(Product product);
}
