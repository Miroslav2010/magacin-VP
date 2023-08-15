package mk.ukim.finki.wp.magacin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MagacinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagacinApplication.class, args);
    }
}
