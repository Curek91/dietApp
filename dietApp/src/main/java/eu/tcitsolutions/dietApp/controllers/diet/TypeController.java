package eu.tcitsolutions.dietApp.controllers.diet;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.TypeDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.diet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {

    @Autowired
    TypeService typeService;

    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @CrossOrigin(origins = "${cors.host}")
    @GetMapping("/types")
    public @ResponseBody
    ResponseEntity<List<Type>> typesList(){
        return new ResponseEntity<>(typeService.getTypes(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @GetMapping("/type/{id}")
    public @ResponseBody
    ResponseEntity<Type> getType(@PathVariable Long id){
        Type type = typeService.getType(id);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @PostMapping(value="/type", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Type> createType(@RequestBody TypeDTO source){
        typeService.saveType(source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @PutMapping(value = "/type/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Type> updateType(@PathVariable("id") Long id, @RequestBody TypeDTO source){
        typeService.updateType(id, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @DeleteMapping(value = "/type/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Type> updateType(@PathVariable("id") Long id){
        typeService.removeType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
