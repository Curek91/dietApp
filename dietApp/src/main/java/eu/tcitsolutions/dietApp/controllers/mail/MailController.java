package eu.tcitsolutions.dietApp.controllers.mail;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
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

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value="/api/sendEmail")
    public @ResponseBody
    ResponseEntity send(@RequestBody DietDTO source) {
        emailService.sendEmail("bukszpanek91@gmail.com", "Dieta", "Tresc diety");
        return new ResponseEntity(HttpStatus.OK);
    }

}
