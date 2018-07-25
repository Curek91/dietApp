package eu.tcitsolutions.dietApp.core.service;

import eu.tcitsolutions.dietApp.core.domain.entity.Type;

import java.util.List;

public interface TypeService {
    public List<Type> getTypes();

    public Type getType(Long id);

    public void saveProduct(Type source);

    public void removeType(Long id);

    public void updateType(Type source);
}
