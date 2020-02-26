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
    private DTOMappingService dtoMappingService;

    public TypeServiceImpl(@Qualifier("hibernateTypeRepository") TypeRepository typeRepository, DTOMappingService dtoMappingService){
        this.typeRepository = typeRepository;
        this.dtoMappingService = dtoMappingService;
    }

    @Override
    public List<Type> getTypes() {
        return typeRepository.getTypes();
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getType(id);
    }

    @Override
    public void saveType(TypeDTO source) {
        typeRepository.save(dtoMappingService.createEntity(source));
    }

    @Override
    public void removeType(Long id) {
        typeRepository.delete(id);
    }

    @Override
    public void updateType(Long id, TypeDTO source) {
        typeRepository.update(dtoMappingService.createEntity(id ,source));
    }
}
