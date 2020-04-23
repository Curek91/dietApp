package eu.tcitsolutions.dietApp.core.diet.domain.repository;

import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DietRepository {
    List<Diet> findAll();

    Page<Diet> findAll(Pageable page);

    Optional<Diet> findById(Long id);

    Diet save(Diet client);

    void deleteById(Long id);
}
