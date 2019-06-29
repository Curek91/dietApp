package eu.tcitsolutions.dietApp.core.diet.domain.repository;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;

import java.util.List;

public interface DietRepository {
    public List<Diet> getDiets();

    public Diet getDiet(Long id);

    public void save(Diet diet);

    public void delete(Diet diet);

    public void delete(Long id);

    public void update(Diet diet);
}
