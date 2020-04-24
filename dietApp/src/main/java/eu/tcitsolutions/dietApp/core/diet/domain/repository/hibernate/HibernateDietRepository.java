package eu.tcitsolutions.dietApp.core.diet.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
interface HibernateDietRepository extends DietRepository, JpaRepository<Diet, Long> {

    @Override
    List<Diet> findDietsByClient_ClientNo(@Param("client_no") Long clientNo);
}


