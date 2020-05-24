package eu.tcitsolutions.dietApp.core.client.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import eu.tcitsolutions.dietApp.core.client.service.DTOClientMappingService;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.DietRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
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
    private DietRepository dietRepository;
    private DTOClientMappingService dtoClientMappingService;

    public ClientServiceImpl(ClientRepository clientRepository, DietRepository dietRepository, DTOClientMappingService dtoClientMappingService){
        this.clientRepository = clientRepository;
        this.dietRepository = dietRepository;
        this.dtoClientMappingService = dtoClientMappingService;
    }

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(dtoClientMappingService::createDTO).collect(Collectors.toList());
    }

    @Override
    public Page<ClientDTO> getClients(Pageable page) {
        return clientRepository.findAll(page).map((dtoClientMappingService::createDTO));
    }

    @Override
    public Page<ClientDTO> getNewestClients(Pageable page) {
        return clientRepository.findNewestClients(page).map((dtoClientMappingService::createDTO));
    }

    @Override
    public ClientDTO getClient(Long id) {
        return dtoClientMappingService.createDTO(clientRepository.findById(id).get());
    }

    @Override
    public void saveClient(ClientDTO source) {
        Client client = dtoClientMappingService.createEntity(source);
        if (client.getClientNo() == null)
            client.setClientNo(clientRepository.getClientSeqNoNextVal());
        clientRepository.save(client);
    }

    @Override
    public void removeClient(Long clientNo) {
        dietRepository.findDietsByClient_ClientNo(clientNo).stream().forEach((diet) -> dietRepository.deleteById(diet.getId()));
        clientRepository.findClientsByClientNo(clientNo).stream().forEach((c) -> clientRepository.deleteById(c.getId()));
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
        Client clientNewVersion = dtoClientMappingService.createEntity(source);
        clientNewVersion.setClientNo(clientNo);
        clientRepository.save(clientNewVersion);
    }

    @Override
    public List<ClientDTO> getClientVersions(Long clientNo) {
        return clientRepository.findClientsByClientNo(clientNo).stream().map(dtoClientMappingService::createDTO).collect(Collectors.toList());
    }

    @Override
    public Client getNewestClientVersion(Long clientNo) {
        Client client = clientRepository.findFirstByClientNoOrderByIdDesc(clientNo);
        return client;
    }

    @Override
    public Page<ClientDTO> getClientByName(Pageable page, String name) {
        return clientRepository.findClientsByFirstnameContainsOrLastnameContains(name, name, page).map((dtoClientMappingService::createDTO));
    }

    @Override
    public Page<ClientDTO> getNewestClientsByFirstnameContainsOrLastnameContains(Pageable page, String name) {
        return clientRepository.findNewestClientsByFirstnameContainsOrLastnameContains(name, name, page).map((dtoClientMappingService::createDTO));
    }
}
