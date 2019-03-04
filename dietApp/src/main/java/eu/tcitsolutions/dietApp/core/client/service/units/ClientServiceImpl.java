package eu.tcitsolutions.dietApp.core.client.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    DTOClientMappingService dtoClientMappingService;

    @Override
    public List<Client> getClients() {
        return clientRepository.getClients();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.getClient(id);
    }

    @Override
    public void saveClient(ClientDTO source) {
        clientRepository.save(dtoClientMappingService.createEntity(source));
    }

    @Override
    public void removeClient(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public void updateClient(Long id, ClientDTO source) {
        clientRepository.update(dtoClientMappingService.createEntity(id ,source));
    }
}
