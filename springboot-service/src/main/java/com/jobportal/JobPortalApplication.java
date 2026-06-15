package com.jobportal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class JobPortalApplication {
    public static void main(String[] args) {
        String dbUrl = System.getenv("SPRING_DATASOURCE_URL");
        if (dbUrl != null && dbUrl.startsWith("postgresql://")) {
            int atIndex = dbUrl.indexOf('@');
            String jdbcUrl;
            if (atIndex != -1) {
                jdbcUrl = "jdbc:postgresql://" + dbUrl.substring(atIndex + 1);
            } else {
                jdbcUrl = "jdbc:postgresql://" + dbUrl.substring("postgresql://".length());
            }
            System.setProperty("SPRING_DATASOURCE_URL", jdbcUrl);
        }
        SpringApplication.run(JobPortalApplication.class, args);
    }
}
