package eu.tcitsolutions.dietApp.controllers.diet;

import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Meal;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.MealProduct;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.MealRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import eu.tcitsolutions.dietApp.core.diet.service.MealService;
import eu.tcitsolutions.dietApp.core.diet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DietController {

    private DietService dietService;

    public DietController(DietService dietService){
        this.dietService = dietService;
    }

    @CrossOrigin(origins = "${cors.host}")
    @GetMapping("/diets/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<Diet>> dietsList(@PathVariable Long clientId){
        return new ResponseEntity<List<Diet>>(dietService.getDiets(clientId), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @PostMapping(value="/diet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Diet> createDiet(@RequestBody DietDTO source){
        return new ResponseEntity<>(dietService.saveDiet(source), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @GetMapping("/diet/{id}")
    public @ResponseBody
    ResponseEntity<DietDTO> getDiet(@PathVariable Long id){
        return new ResponseEntity<>(dietService.getDiet(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @PutMapping(value = "/diet/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Diet> updateDiet(@PathVariable("id") Long id, @RequestBody DietDTO source){
        dietService.updateDiet(id, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @DeleteMapping(value = "/diet/{id}")
    public @ResponseBody
    ResponseEntity<Diet> deleteDiet(@PathVariable("id") Long id){
        dietService.removeDiet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
