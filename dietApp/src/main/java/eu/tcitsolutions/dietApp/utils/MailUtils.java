package eu.tcitsolutions.dietApp.utils;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;

import java.util.stream.Collectors;

public class MailUtils {
    public static String createFormatMailText(DietDTO diet){
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<html><table border=\"1\">");
        for (MealDTO meal: diet.getMeals().stream().sorted((x, y) -> x.getMealNo() - y.getMealNo()).collect(Collectors.toList())){
            mailContent.append("<tr><td>");
            mailContent.append("Posiłek: ").append(meal.getMealNo());
            mailContent.append("</td></tr>");
            for(ProductDTO prod: meal.getProducts()) {
                mailContent.append("<tr><td>");
                mailContent.append(prod.getName()).append(" ").append(prod.getWeight());
                mailContent.append("</td></tr>");
            }
        }
        mailContent.append("</table></html>");
        return mailContent.toString();
    }
}
