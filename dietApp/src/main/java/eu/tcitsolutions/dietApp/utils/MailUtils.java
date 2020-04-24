package eu.tcitsolutions.dietApp.utils;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.DietDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.MealDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Diet;

import java.util.stream.Collectors;

public class MailUtils {
    public static String createFormatMailText(DietDTO diet){
/*        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<html><table border=\"1\">");
        mailContent.append("<tr>");
        mailContent.append("<td>Posiłki</td>");
        mailContent.append("<td>Dni treningowe</td>");
        mailContent.append("<td>Suplementacja</td>");
        mailContent.append("</tr>");
        for (MealDTO meal: diet.getMeals().stream().sorted((x, y) -> x.getMealNo() - y.getMealNo()).collect(Collectors.toList())){
            mailContent.append("<tr>");
            mailContent.append("<td>Posiłek: " + meal.getMealNo() + " </td>");
            mailContent.append("<td></td>");
            mailContent.append("<td>" + meal.getSuplements().replaceAll("\\r|\\n", "<br/>") + "</td>");
            mailContent.append("</tr>");
            for(ProductDTO prod: meal.getProducts()) {
                mailContent.append("<tr>");
                mailContent.append("<td></td>");
                mailContent.append("<td>" + prod.getName()).append(" ").append(prod.getWeight() + " g" + "</td>");
                mailContent.append("<td></td>");
                mailContent.append("</tr>");
            }
        }
        mailContent.append("</table></html>");
        return mailContent.toString();*/

        return "asd";
    }
}
