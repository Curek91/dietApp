package eu.tcitsolutions.dietApp.core.client.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private DTOClientMappingService dtoClientMappingService;

    public ClientServiceImpl(ClientRepository clientRepository, DTOClientMappingService dtoClientMappingService){
        this.clientRepository = clientRepository;
        this.dtoClientMappingService = dtoClientMappingService;
    }

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.getClients().stream().map((c) -> dtoClientMappingService.createDTO(c)).collect(Collectors.toList()) ;
    }

    @Override
    public List<ClientDTO> getNewestClients() {
        return clientRepository.getNewestClients().stream().map((c) -> dtoClientMappingService.createDTO(c)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(Long id) {
        return dtoClientMappingService.createDTO(clientRepository.getClient(id));
    }

    @Override
    public void saveClient(ClientDTO source) {
        clientRepository.save(dtoClientMappingService.createEntity(source));
    }

    @Override
    public void removeClient(Long clientNo) {
        clientRepository.delete(clientNo);
    }

    @Override
    public void updateClient(Long id, ClientDTO source) {
        clientRepository.update(dtoClientMappingService.createEntity(id ,source));
    }

    @Override
    public void createNewVersion(Long clientNo, ClientDTO source) {
        Client client = dtoClientMappingService.createEntity(source);
        Long lastId = clientRepository.getLastIdForClientNo(clientNo);
        client.setPreClientId(lastId);
        client.setClientNo(clientNo);
        Client newClient = clientRepository.save(client);
        System.out.println(newClient.getId());
        client = clientRepository.getClient(lastId);
        client.setSucClientId(newClient.getId());
        clientRepository.update(client);
    }

    @Override
    public List<ClientDTO> getClientVersions(Long clientNo) {
        return clientRepository.getClientVersions(clientNo).stream().map((c) -> dtoClientMappingService.createDTO(c)).collect(Collectors.toList());
    }
}
