package eu.tcitsolutions.dietApp.core.client.service;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getClients();

    public List<Client> getNewestClients();

    public Client getClient(Long id);

    public void saveClient(ClientDTO source);

    public void removeClient(Long id);

    public void updateClient(Long id, ClientDTO source);

    public void createNewVersion(Long clientNo, ClientDTO source);

    public List<Client> getClientVersions(Long clientNo);
}
