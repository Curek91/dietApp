package eu.tcitsolutions.dietApp.core.diet.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
interface HibernateMealRepository extends MealRepository, JpaRepository<Meal, Long> {
}
