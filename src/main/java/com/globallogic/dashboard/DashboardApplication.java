package com.globallogic.dashboard;


import com.globallogic.dashboard.service.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DashboardApplication implements ApplicationRunner {

    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoader.loadData();
    }
}
