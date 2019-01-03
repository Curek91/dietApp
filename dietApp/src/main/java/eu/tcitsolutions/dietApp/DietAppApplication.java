package eu.tcitsolutions.dietApp;

import eu.tcitsolutions.dietApp.controllers.ProductController;
import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.domain.entity.Type;
import eu.tcitsolutions.dietApp.core.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.domain.repository.TypeRepository;
import eu.tcitsolutions.dietApp.core.domain.repository.hibernate.HibernateProductRepository;
import eu.tcitsolutions.dietApp.core.domain.repository.hibernate.HibernateTypeRepository;
import eu.tcitsolutions.dietApp.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

@SpringBootApplication
@EnableJpaAuditing
public class DietAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(DietAppApplication.class, args);
        System.out.println("Aplikacja wystartowala");


    }
}
