package com.jobportal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class JobPortalApplication {
    public static void main(String[] args) {
        String dbUrl = System.getenv("SPRING_DATASOURCE_URL");
        if (dbUrl != null && dbUrl.startsWith("postgresql://")) {
            System.setProperty("SPRING_DATASOURCE_URL", "jdbc:" + dbUrl);
        }
        SpringApplication.run(JobPortalApplication.class, args);
    }
}
