package eu.tcitsolutions.dietApp.controllers.client;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<ClientDTO>> clientsList() {
        List<ClientDTO> clientList = clientService.getClients();
        return new ResponseEntity<List<ClientDTO>>(clientList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/newestClients")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<ClientDTO>> newestClients() {
        List<ClientDTO> clientList = clientService.getNewestClients();
        return new ResponseEntity<List<ClientDTO>>(clientList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client = clientService.getClient(id);
        return new ResponseEntity<ClientDTO>(client, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value = "/client/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Client> createClient(@RequestBody ClientDTO source) {
        clientService.saveClient(source);
        return new ResponseEntity<Client>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.PUT, value = "/client/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO source) {
        clientService.updateClient(id, source);
        return new ResponseEntity<Client>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.DELETE, value = "/client/delete/{clientNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity deleteClient(@PathVariable("clientNo") Long id) {
        clientService.removeClient(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value = "/client/createNewVersion/{clientNo}")
    public @ResponseBody
    ResponseEntity<Client> createNewVersion(@PathVariable("clientNo") Long clientNo, @RequestBody ClientDTO source) {
        clientService.createNewVersion(clientNo, source);
        return new ResponseEntity<Client>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/clientVersions/{clientNo}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<ClientDTO>> clientVersions(@PathVariable Long clientNo) {
        List<ClientDTO> clientList = clientService.getClientVersions(clientNo);
        return new ResponseEntity<List<ClientDTO>>(clientList, HttpStatus.OK);
    }
}
