package eu.tcitsolutions.dietApp.core.client.service;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;

import java.util.List;

public interface ClientService {
    public List<ClientDTO> getClients();

    public List<ClientDTO> getNewestClients();

    public ClientDTO getClient(Long id);

    public void saveClient(ClientDTO source);

    public void removeClient(Long clientNo);

    public void updateClient(Long id, ClientDTO source);

    public void createNewVersion(Long clientNo, ClientDTO source);

    public List<ClientDTO> getClientVersions(Long clientNo);
}
