package eu.tcitsolutions.dietApp.core.client.service;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;

public interface DTOClientMappingService {

    //Create DTO's
    ClientDTO createDTO(Client source);

    //create Entities
    Client createEntity(ClientDTO source);
}
