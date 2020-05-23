package eu.tcitsolutions.dietApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class DietAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(DietAppApplication.class, args);
        System.out.println("Application has started");
    }

    @PostConstruct
    void started() {
        // set JVM timezone as UTC
        //TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw+"));
    }
}
