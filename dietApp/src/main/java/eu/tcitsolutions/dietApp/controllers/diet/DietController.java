package eu.tcitsolutions.dietApp.controllers.diet;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import eu.tcitsolutions.dietApp.core.diet.service.MealService;
import eu.tcitsolutions.dietApp.core.diet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private MealService mealService;

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value="/diet/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Diet> createDiet(@RequestBody DietDTO source){
        dietService.saveDiet(source);
        System.out.println("Controller is working");
        return new ResponseEntity<Diet>(new Diet(), HttpStatus.OK);
    }

}
