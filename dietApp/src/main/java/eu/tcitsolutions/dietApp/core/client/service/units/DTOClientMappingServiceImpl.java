package eu.tcitsolutions.dietApp.core.client.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Lazy
@Service
@Transactional
public class DTOClientMappingServiceImpl implements DTOClientMappingService{


    public DTOClientMappingServiceImpl(){
    }

    @Override
    public ClientDTO createDTO(Client source) {
        return new ClientDTO(source.getFirstname(),
                source.getLastname(),
                source.getAge(),
                source.getWeight(),
                source.getHeight(),
                source.getEmail(),
                source.getTelephone(),
                source.getBiceps(),
                source.getChest(),
                source.getWaist(),
                source.getThigh(),
                source.getCreationTime(),
                source.getClientNo());
    }

    @Override
    public Client createEntity(ClientDTO source){
        return new Client(source.getFirstname(),
                source.getLastname(),
                source.getAge(),
                source.getWeight(),
                source.getHeight(),
                source.getEmail(),
                source.getTelephone(),
                source.getBiceps(),
                source.getChest(),
                source.getWaist(),
                source.getThigh(),
                source.getClientNo());
    }
}
