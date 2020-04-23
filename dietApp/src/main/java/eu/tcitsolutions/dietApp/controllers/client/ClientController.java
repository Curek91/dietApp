package eu.tcitsolutions.dietApp.controllers.client;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${cors.host}")
@RestController
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients", params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<ClientDTO>> clientsList() {
        List<ClientDTO> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<PagedResources<Client>> clientsList(Pageable page, PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toResource(clientService.getClients(page)));
    }

    @GetMapping( value = "/clients/newests")
    public ResponseEntity<PagedResources<Client>> newestClients(Pageable page, PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toResource(clientService.getNewestClients(page)));
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client = clientService.getClient(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO source) {
        clientService.saveClient(source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO source) {
        clientService.updateClient(id, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/clients/{id}")
    public ResponseEntity<Client> updateClientByPatch(@PathVariable("id") Long id, @RequestBody ClientDTO source) {
        clientService.updateClient(id, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{clientNo}")
    public ResponseEntity deleteClient(@PathVariable("clientNo") Long id) {
        clientService.removeClientById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/clients/createNewVersion/{clientNo}")
    public ResponseEntity<Client> createNewVersion(@PathVariable("clientNo") Long clientNo, @RequestBody ClientDTO source) {
        clientService.createNewVersion(clientNo, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "clients/clientVersions/{clientNo}")
    public ResponseEntity<List<ClientDTO>> clientVersions(@PathVariable Long clientNo) {
        List<ClientDTO> clientList = clientService.getClientVersions(clientNo);
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}
