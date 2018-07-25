package eu.tcitsolutions.dietApp.core.domain.repository;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.entity.Type;

import java.util.List;

public interface TypeRepository {
    public List<Type> getTypes();

    public Type getType(Long id);

    public void save(Type type);

    public void delete(Type type);

    public void delete(Long id);

    public void update(Type type);
}
