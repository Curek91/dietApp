package eu.tcitsolutions.dietApp.core.diet.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.TypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateDietRepository implements DietRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Diet> getDiets(Long clientId) {
        String hql = "select d from diet d where d.client.id = :client_id";
        return (List<Diet>) entityManager.createQuery(hql).setParameter("client_id", clientId).getResultList();
    }

    @Override
    public Diet getDiet(Long id) {
        return entityManager.find(Diet.class, id);
    }

    @Override
    public Diet save(Diet diet) {
        entityManager.persist(diet);
        entityManager.flush();
        return diet;
    }

    @Override
    public void delete(Diet diet) {
        entityManager.remove(diet);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Diet.class, id));
    }

    @Override
    public void update(Diet diet) {
        entityManager.merge(diet);
    }
}
