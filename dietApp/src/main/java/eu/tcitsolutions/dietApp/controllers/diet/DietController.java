package eu.tcitsolutions.dietApp.controllers.diet;

import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietGetDietDTO;
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

@CrossOrigin(origins = "${cors.host}")
@RestController
public class DietController {

    private DietService dietService;

    public DietController(DietService dietService){
        this.dietService = dietService;
    }

    @PostMapping(value="/diets/{clientNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity<Diet> createDiet(@RequestBody DietDTO source, @PathVariable Long clientNo){
        Diet diet = dietService.saveDiet(clientNo, source);
        return ResponseEntity.ok(diet);
    }

    @GetMapping(value = "/diets/byClientNo/{clientNo}")
    public
    ResponseEntity<List<DietDTO>> dietsByClientNoList(@PathVariable Long clientNo){
        List<DietDTO> dietList = dietService.getDiets(clientNo);
        return ResponseEntity.ok(dietList);
    }

    @GetMapping(value = "/diets/{id}")
    public @ResponseBody
    ResponseEntity<DietGetDietDTO> getDiet(@PathVariable Long id){
        DietGetDietDTO dietGetDietDTO = dietService.getDiet(id);
        return ResponseEntity.ok(dietGetDietDTO);
    }

    @PutMapping(value = "/diets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity<Diet> updateDiet(@RequestBody DietDTO source, @PathVariable("id") Long id){
        Diet diet = dietService.updateDiet(id, source);
        return ResponseEntity.ok(diet);
    }

    @DeleteMapping(value = "/diets/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable("id") Long id){
        dietService.removeDiet(id);
        return ResponseEntity.ok("Diet id: " + id + " Deleted with success");
    }




}
