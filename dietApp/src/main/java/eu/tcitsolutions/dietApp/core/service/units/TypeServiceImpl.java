package eu.tcitsolutions.dietApp.core.service.units;

import eu.tcitsolutions.dietApp.core.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> getTypes() {
        return typeRepository.getTypes();
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getType(id);
    }

    @Override
    public void saveProduct(Type source) {
        typeRepository.save(source);
    }

    @Override
    public void removeType(Long id) {
        typeRepository.delete(id);
    }

    @Override
    public void updateType(Type source) {
        typeRepository.update(source);
    }
}
