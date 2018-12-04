package com.globallogic.dashboard;


import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DashboardApplication {

   public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./dashboardGL2;DB_CLOSE_ON_EXIT=FALSE", "sa", null).load();

       flyway.migrate();
   }

}
