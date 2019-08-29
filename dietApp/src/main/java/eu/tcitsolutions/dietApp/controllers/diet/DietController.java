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

    @Autowired
    private DietService dietService;

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value="/diet/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Diet> createDiet(@RequestBody DietDTO source){
        dietService.saveDiet(source);
        return new ResponseEntity<Diet>(new Diet(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/diets/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<Diet>> dietsList(@PathVariable Long clientId){
        List<Diet> dietList = dietService.getDiets(clientId);
        return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/diet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<DietDTO> getDiet(@PathVariable Long id){
        DietDTO dietDTO = dietService.getDiet(id);
        return new ResponseEntity<DietDTO>(dietDTO, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.PUT, value = "/diet/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Diet> updateDiet(@PathVariable("id") Long id, @RequestBody DietDTO source){
        dietService.updateDiet(id, source);
        return new ResponseEntity<Diet>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.DELETE, value = "/diet/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Diet> deleteDiet(@PathVariable("id") Long id){
        dietService.removeDiet(id);
        return new ResponseEntity<Diet>(HttpStatus.OK);
    }

}
