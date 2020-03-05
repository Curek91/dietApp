package eu.tcitsolutions.dietApp.core.client.domain.repository;


import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;

import java.util.List;

public interface ClientRepository {
    public List<Client> getClients();

    public List<Client> getNewestClients();

    public Client getClient(Long id);

    public Client save(Client client);

    public void delete(Client client);

    public void delete(Long id);

    public void update(Client client);

    public Long getLastIdForClientNo(Long clientNo);

    public List<Client> getClientVersions(Long clientNo);
}
