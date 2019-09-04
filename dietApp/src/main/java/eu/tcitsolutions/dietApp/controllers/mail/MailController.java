package eu.tcitsolutions.dietApp.controllers.mail;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import eu.tcitsolutions.dietApp.core.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private DietService dietService;

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value="/api/sendEmail")
    public @ResponseBody
    ResponseEntity send(@RequestBody Long dietId) {
        DietDTO diet = dietService.getDiet(dietId);
        StringBuilder mailContent = new StringBuilder();
        for (MealDTO meal: diet.getMeals()){
            System.out.println("numer posilku: " + meal.getMealNo());
            mailContent.append("Posiłek: ").append(meal.getMealNo()).append("\r\n");
            for(ProductDTO prod: meal.getProducts()){
                System.out.println("Nazwa produktu: " + prod.getName());
                mailContent.append("\t").append(prod.getName()).append("\r\n");
            }
        }
        System.out.println(mailContent);
        emailService.sendEmail("bukszpanek91@gmail.com", "Dieta", mailContent.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

}
