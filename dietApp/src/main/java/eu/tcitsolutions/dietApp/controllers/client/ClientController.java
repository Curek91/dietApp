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

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @CrossOrigin(origins = "${cors.host}")
    @GetMapping("/clients")
    public @ResponseBody
    ResponseEntity<List<Client>> clientsList(){
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @GetMapping("/client/{id}")
    public @ResponseBody
    ResponseEntity<Client> getClient(@PathVariable Long id){
        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @PostMapping(value="/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Client> createClient(@RequestBody ClientDTO source){
        clientService.saveClient(source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @PutMapping(value = "/client/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO source){
        clientService.updateClient(id, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @DeleteMapping(value = "/client/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Client> updateType(@PathVariable("id") Long id){
        clientService.removeClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
