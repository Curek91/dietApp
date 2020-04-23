package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).get();
    }

}
