package mk.finki.ukim.mk.laba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LabAApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabAApplication.class, args);
    }

}
