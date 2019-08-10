package eu.tcitsolutions.dietApp.core.diet.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProducts() {
        String hql = "select p from product p";
        return (List<Product>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Product getProduct(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product save(Product product) {
        entityManager.persist(product);
        entityManager.flush();
        return product;
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(product);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Product.class, id));
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }
}
