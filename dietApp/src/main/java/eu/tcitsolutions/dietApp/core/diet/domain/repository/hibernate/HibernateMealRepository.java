package eu.tcitsolutions.dietApp.core.diet.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Meal> getMeals() {
        String hql = "select m from meal m";
        return (List<Meal>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Meal getMeal(Long id) {
        return entityManager.find(Meal.class, id);
    }

    @Override
    public Meal save(Meal meal) {
        entityManager.persist(meal);
        entityManager.flush();
        return meal;
    }

    @Override
    public void delete(Meal meal) {
        entityManager.remove(meal);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Meal.class, id));
    }

    @Override
    public void update(Meal meal) {
        entityManager.merge(meal);
    }
}
