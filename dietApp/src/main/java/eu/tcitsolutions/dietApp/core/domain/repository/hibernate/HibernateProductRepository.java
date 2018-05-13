package eu.tcitsolutions.dietApp.core.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        String hql = "select p from Product p";
        return (List<Product>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Product getProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    @Override
    public Product load(Long id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Product product) {

    }
}
