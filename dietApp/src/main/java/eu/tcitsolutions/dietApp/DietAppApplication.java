package eu.tcitsolutions.dietApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class DietAppApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(DietAppApplication.class, args);
        System.out.println("Application has started");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DietAppApplication.class);
    }

    @PostConstruct
    void started() {
        // set JVM timezone as UTC
        //TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw+"));
    }
}
