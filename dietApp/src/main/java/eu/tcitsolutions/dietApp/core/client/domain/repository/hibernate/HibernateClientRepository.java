package eu.tcitsolutions.dietApp.core.client.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateClientRepository implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> getClients() {
        String hql = "select c from client c";
        return (List<Client>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Client getClient(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public Client save(Client client) {
        entityManager.persist(client);
        entityManager.flush();
        System.out.println(client.getId());
        return client;
    }

    @Override
    public void delete(Client client) {
        entityManager.remove(client);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Client.class, id));
    }

    @Override
    public void update(Client client) {
        entityManager.merge(client);
    }

}
