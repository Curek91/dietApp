package eu.tcitsolutions.dietApp.core.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.domain.repository.TypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateTypeRepository implements TypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Type> getTypes() {
        String hql = "select t from Type t";
        return (List<Type>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Type getType(Long id) {
        return entityManager.find(Type.class, id);
    }

    @Override
    public void save(Type type) {
        entityManager.persist(type);
    }

    @Override
    public void delete(Type type) {
        entityManager.remove(type);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Type.class, id));
    }

    @Override
    public void update(Type type) {
        entityManager.merge(type);
    }
}
