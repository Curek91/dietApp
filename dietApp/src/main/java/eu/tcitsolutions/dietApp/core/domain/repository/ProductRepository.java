package eu.tcitsolutions.dietApp.core.domain.repository;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.entity.Type;

import java.util.List;

public interface ProductRepository {

    public List<Product> getProducts();

    public Product getProduct(Long id);

    public void save(Product product);

    public void delete(Product product);

    public void delete(Long id);

    public void update(Product product);
}
