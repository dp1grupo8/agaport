package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication(scanBasePackages={"app.controlador", "app.repositorios"})
public class Application {
    public static void main(String[] args) {
        System.out.println("Version: " + SpringVersion.getVersion());
        SpringApplication.run(Application.class, args);
    }
}