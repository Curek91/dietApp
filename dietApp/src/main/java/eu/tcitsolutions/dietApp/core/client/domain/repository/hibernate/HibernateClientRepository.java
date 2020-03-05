package eu.tcitsolutions.dietApp.core.client.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
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
    public List<Client> getNewestClients() {
        String hql = "select c from client c where c.sucClientId is null";
        return (List<Client>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Client getClient(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public Client save(Client client) {
        if (client.getClientNo() == null) {
            client.setClientNo(((BigInteger) entityManager.createNativeQuery("select nextval('client_no_seq')").getSingleResult()).longValue());
        }
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

    @Override
    public Long getLastIdForClientNo(Long clientNo) {
        String hql = "select c.id from client c where c.clientNo = :clientNo and c.sucClientId is null";
        return (Long) entityManager.createQuery(hql).setParameter("clientNo", clientNo).getSingleResult();
    }

    @Override
    public List<Client> getClientVersions(Long clientNo) {
        String hql = "select c from client c where c.clientNo = :clientNo";
        return (List<Client>) entityManager.createQuery(hql).setParameter("clientNo", clientNo).getResultList();
    }

}
