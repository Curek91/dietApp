package eu.tcitsolutions.dietApp.core.client.service;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;

public interface DTOClientMappingService {

    //Create DTO's
    public ClientDTO createDTO(Client source);

    //create Entities
    public Client createEntity(ClientDTO source);
}
