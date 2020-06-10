package eu.tcitsolutions.dietApp.controllers.client;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Method;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@CrossOrigin(origins = "${cors.host}")
@RestController
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clients", params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<ClientDTO>> clientsList() {
        List<ClientDTO> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clients")
    public ResponseEntity<PagedResources<Client>> clientsList(Pageable page, PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toResource(clientService.getClients(page)));
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping( value = "/clients/newests")
    public ResponseEntity<PagedResources<Client>> newestClients(Pageable page, PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toResource(clientService.getNewestClients(page)));
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client = clientService.getClient(id);
        return ResponseEntity.ok(client);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PostMapping(value = "/clients")
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO source) {
        clientService.saveClient(source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO source) {
        clientService.updateClient(id, source);
        return ResponseEntity.ok(source);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PatchMapping(value = "/clients/{id}")
    public ResponseEntity<Client> updateClientByPatch(@PathVariable("id") Long id, @RequestBody ClientDTO source) {
        clientService.updateClient(id, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @DeleteMapping(value = "/clients/{clientNo}")
    public ResponseEntity deleteClient(@PathVariable("clientNo") Long id) {
        clientService.removeClient(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PostMapping(value = "/clients/createNewVersion/{clientNo}")
    public ResponseEntity<Client> createNewVersion(@PathVariable("clientNo") Long clientNo, @RequestBody ClientDTO source) {
        clientService.createNewVersion(clientNo, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "clients/clientVersions/{clientNo}")
    public ResponseEntity<List<ClientDTO>> clientVersions(@PathVariable Long clientNo) {
        List<ClientDTO> clientList = clientService.getClientVersions(clientNo);
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clientsByFirstnameOrLastname", params = {"name"})
    public ResponseEntity<PagedResources<Client>> clientsListByFirstnameOrLastname(@RequestParam String name, Pageable page, PagedResourcesAssembler assembler) {
        PagedResources<Client> pr = assembler.toResource(clientService.getClientByName(page, name), new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString()).withRel("example"));
        return ResponseEntity.ok(pr);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clientsByFirstnameOrLastname", params = {"!name"})
    public ResponseEntity<PagedResources<Client>> clientsListByFirstnameOrLastname(Pageable page, PagedResourcesAssembler assembler) {
        PagedResources<Client> pr = assembler.toResource(clientService.getClientByName(page, ""), new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString()).withRel("example"));
        return ResponseEntity.ok(pr);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clientsNewestsByFirstnameOrLastname", params = {"name"})
    public ResponseEntity<PagedResources<Client>> clientsNewestsListByFirstnameOrLastname(@RequestParam String name, Pageable page, PagedResourcesAssembler assembler) {
        PagedResources<Client> pr = assembler.toResource(clientService.getNewestClientsByFirstnameContainsOrLastnameContains(page, name), new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString()).withRel("example"));
        return ResponseEntity.ok(pr);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/clientsNewestsByFirstnameOrLastname", params = {"!name"})
    public ResponseEntity<PagedResources<Client>> clientsNewestsListByFirstnameOrLastname(Pageable page, PagedResourcesAssembler assembler) {
        PagedResources<Client> pr = assembler.toResource(clientService.getNewestClientsByFirstnameContainsOrLastnameContains(page, ""), new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString()).withRel("example"));
        return ResponseEntity.ok(pr);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "clients/newestByClientNo/{clientNo}")
    public ResponseEntity<Client> newestClientByClientNo(@PathVariable Long clientNo) {
        Client client = clientService.getNewestClientVersion(clientNo);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
