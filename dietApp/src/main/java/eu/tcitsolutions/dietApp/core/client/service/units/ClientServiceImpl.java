package eu.tcitsolutions.dietApp.core.client.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return clientRepository.findAll().stream().map((c) -> dtoClientMappingService.createDTO(c)).collect(Collectors.toList()) ;
    }

    @Override
    public Page<Client> getClients(Pageable page) {
        return clientRepository.findAll(page);
    }

    @Override
    public Page<Client> getNewestClients(Pageable page) {
        return clientRepository.findNewestClients(page);
    }

    @Override
    public ClientDTO getClient(Long id) {
        return dtoClientMappingService.createDTO(clientRepository.findById(id).get());
    }

    @Override
    public void saveClient(ClientDTO source) {
        clientRepository.save(dtoClientMappingService.createEntity(source));
    }

    @Override
    public void removeClient(Long clientNo) {
        clientRepository.deleteById(clientNo);
    }

    @Override
    public void removeClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void updateClient(Long id, ClientDTO source) {
        Client clientToUpdate = dtoClientMappingService.createEntity(source);
        clientToUpdate.setId(id);
        clientRepository.save(clientToUpdate);
    }

    @Override
    public void updateClientByPatch(Long id, ClientDTO source) {
        Client clientToUpdate = dtoClientMappingService.createEntity(source);
        clientToUpdate.setId(id);
        clientRepository.save(clientToUpdate);
    }

    @Override
    public void createNewVersion(Long clientNo, ClientDTO source) {
        //Mock
    }

    @Override
    public List<ClientDTO> getClientVersions(Long clientNo) {
        return clientRepository.findClientsByClientNo(clientNo).stream().map((c) -> dtoClientMappingService.createDTO(c)).collect(Collectors.toList());
    }
}
