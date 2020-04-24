package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietGetDietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import eu.tcitsolutions.dietApp.core.diet.utils.DietUtils;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Setter
public class DietServiceImpl implements DietService {
    
    private DietRepository dietRepository;
    private DTOMappingService dtoMappingService;
    private ClientService clientService;

    public DietServiceImpl(DietRepository dietRepository,DTOMappingService dtoMappingService, ClientService clientService){
        this.dietRepository = dietRepository;
        this.dtoMappingService = dtoMappingService;
        this.clientService = clientService;
    }

    @Override
    public List<DietDTO> getDiets(Long clientNo) {
        return dietRepository.findDietsByClient_ClientNo(clientNo)
                .stream()
                .map((d) -> new DietDTO(d.getId(), null, DietUtils.calculateKcal(d), d.getModifiedBy(), d.getModificationTime()))
                .collect(Collectors.toList());
    }

    @Override
    public DietGetDietDTO getDiet(Long id) {
        DietGetDietDTO dietGetDietDTO = dtoMappingService.createDTO(dietRepository.findById(id).get());
        return dietGetDietDTO;
    }

    @Override
    public Diet saveDiet(Long clientNo, DietDTO source) {
       Diet diet = dtoMappingService.createEntity(source);
       diet.setClient(clientService.getNewestClientVersion(clientNo));
       return dietRepository.save(diet);
    }

    @Override
    public void removeDiet(Long id) {
        dietRepository.deleteById(id);
    }

    @Override
    public Diet updateDiet(Long id, DietDTO source) {
        Client client = dietRepository.findById(id).get().getClient();
        Diet diet = dtoMappingService.createEntity(source);
        diet.setId(id);
        diet.setClient(client);
        return dietRepository.save(diet);
    }
}
