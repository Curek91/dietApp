package eu.tcitsolutions.dietApp.controllers.mail;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.service.DietService;
import eu.tcitsolutions.dietApp.core.mail.service.EmailService;
import eu.tcitsolutions.dietApp.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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
        String mailText = MailUtils.createFormatMailText(diet);
        System.out.println(mailText);
        emailService.sendEmail("bukszpanek91@gmail.com", "Dieta", mailText);
        return new ResponseEntity(HttpStatus.OK);
    }
}
