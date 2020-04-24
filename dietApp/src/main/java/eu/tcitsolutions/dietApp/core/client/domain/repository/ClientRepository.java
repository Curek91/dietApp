package eu.tcitsolutions.dietApp.core.client.domain.repository;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAll();

    Page<Client> findAll(Pageable page);

    Optional<Client> findById(Long id);

    List<Client> findClientsByClientNo(Long clientNo);

    Client save(Client client);

    void deleteById(Long id);

    Page<Client> findNewestClients(Pageable pageable);

    Client findFirstByClientNoOrderByIdDesc(Long clientNo);
}
