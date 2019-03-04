package eu.tcitsolutions.dietApp.core.client.domain.repository;


import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;

import java.util.List;

public interface ClientRepository {
    public List<Client> getClients();

    public Client getClient(Long id);

    public void save(Client client);

    public void delete(Client client);

    public void delete(Long id);

    public void update(Client client);
}
