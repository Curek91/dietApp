package eu.tcitsolutions.dietApp.core.client.domain.repository.hibernate;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.domain.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface HibernateClientRepository extends ClientRepository, JpaRepository<Client, Long> {

    @Override
    List<Client> findClientsByClientNo(@Param("client_no") Long clientNo);

    @Override
    @Query(value="select c from client c where c.id in (select max(c2.id) from client c2 group by c2.clientNo)")
    Page<Client> findNewestClients(Pageable pageable);

    @Override
    @Query(value="select c from client c where (c.firstname like concat('%', :firstname, '%') or c.lastname like concat('%', :lastname, '%')) and c.id in (select max(c2.id) from client c2 group by c2.clientNo)")
    Page<Client> findNewestClientsByFirstnameContainsOrLastnameContains(String firstname, String lastname, Pageable pageable);

    Client findFirstByClientNoOrderByIdDesc(Long clientNo);

    @Query(nativeQuery = true, value="select nextval('client_no_seq')")
    Long getClientSeqNoNextVal();

    Page<Client> findClientsByFirstnameContainsOrLastnameContains(@Param("firstname") String firstname, @Param("lastname") String lastname, Pageable pageable);
}
