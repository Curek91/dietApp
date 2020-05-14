package eu.tcitsolutions.dietApp.core.diet.domain.repository;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Page<Product> findAll(Pageable page);

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    Product findProductsByName(String name);
}
