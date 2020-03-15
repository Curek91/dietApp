package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Setter
public class DietServiceImpl implements DietService {
    
    private DietRepository dietRepository;

    DTOMappingService dtoMappingService;

    public DietServiceImpl(DietRepository dietRepository, @Qualifier("DTOMappingServiceImpl")  DTOMappingService dtoMappingService){
        this.dietRepository = dietRepository;
        this.dtoMappingService = dtoMappingService;

    }

    @Override
    public List<Diet> getDiets(Long clientNo) {
        return dietRepository.getDiets(clientNo);
    }

    @Override
    public DietDTO getDiet(Long id) {
        DietDTO dietDTO = dtoMappingService.createDTO(dietRepository.getDiet(id));
        return dietDTO;
    }

    @Override
    public Diet saveDiet(DietDTO source) {
       return dietRepository.save(dtoMappingService.createEntity(source));
    }

    @Override
    public void removeDiet(Long id) {
        dietRepository.delete(id);
    }

    @Override
    public void updateDiet(Long id, DietDTO source) {
        dietRepository.update(dtoMappingService.createEntity(id ,source));
    }
}
