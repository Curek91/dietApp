package eu.tcitsolutions.dietApp.controllers;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {

    @Autowired
    TypeService typeService;

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<Type>> typesList(){
        List<Type> typeList = typeService.getTypes();
        return new ResponseEntity<List<Type>>(typeList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Type> getType(@PathVariable Long id){
        Type type = typeService.getType(id);
        return new ResponseEntity<Type>(type, HttpStatus.OK);
    }
}
