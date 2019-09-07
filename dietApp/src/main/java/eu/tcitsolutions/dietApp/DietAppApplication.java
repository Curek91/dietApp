package eu.tcitsolutions.dietApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DietAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(DietAppApplication.class, args);
        System.out.println("Application has started");
        System.out.println("Jebniemy cos");

    }
}
