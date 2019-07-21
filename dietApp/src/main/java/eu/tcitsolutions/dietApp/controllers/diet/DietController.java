package eu.tcitsolutions.dietApp.controllers.diet;

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

@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private MealService mealService;

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value="/diet/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Diet> createDiet(@RequestBody DietDTO source){
        Product p1 = productService.getProduct(1L);
        Product p2 = productService.getProduct(2L);

        Meal meal = new Meal();
        meal.setMealNo(1);
        meal.addProduct(p1, 100);
        meal.addProduct(p2, 200);
        Diet diet = new Diet();
        diet.addMeal(meal);




        dietService.saveDiet(source);
        return new ResponseEntity<Diet>(new Diet(), HttpStatus.OK);
    }

}
