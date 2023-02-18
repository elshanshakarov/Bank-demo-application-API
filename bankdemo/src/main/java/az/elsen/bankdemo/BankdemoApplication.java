package az.elsen.bankdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication //bu annotation main class olmasın bildirir. Boot proyekt buradan start olunur
public class BankdemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BankdemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BankdemoApplication.class);
    }

}
