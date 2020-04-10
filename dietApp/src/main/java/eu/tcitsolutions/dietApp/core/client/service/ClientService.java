package eu.tcitsolutions.dietApp.core.client.service;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    public List<ClientDTO> getClients();

    public Page<Client> getClients(Pageable page);

    public Page<Client> getNewestClients(Pageable page);

    public ClientDTO getClient(Long id);

    public void saveClient(ClientDTO source);

    public void removeClient(Long clientNo);

    public void removeClientById(Long id);

    public void updateClient(Long id, ClientDTO source);

    public void updateClientByPatch(Long id, ClientDTO source);

    public void createNewVersion(Long clientNo, ClientDTO source);

    public List<ClientDTO> getClientVersions(Long clientNo);
}
