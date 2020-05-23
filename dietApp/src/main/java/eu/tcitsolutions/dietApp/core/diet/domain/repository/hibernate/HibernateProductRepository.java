package eu.tcitsolutions.dietApp.core.diet.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
interface HibernateProductRepository extends ProductRepository, JpaRepository<Product, Long> {

    Product findProductsByName(@Param("name") String name);
}
