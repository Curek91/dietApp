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

@CrossOrigin(origins = "${cors.host}")
@RestController
public class TypeController {

    TypeService typeService;

    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @GetMapping(value = "/types", params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<Type>> types(){
        List<Type> types = typeService.getTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping(value = "/types/{id}")
    public
    ResponseEntity<Type> getType(@PathVariable Long id){
        Type type = typeService.getType(id);
        return ResponseEntity.ok(type);
    }
}
