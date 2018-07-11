package com.globallogic.dashboard;


import com.globallogic.dashboard.service.*;
import com.globallogic.dashboard.to.SprintDataCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DashboardApplication implements ApplicationRunner {


    @Autowired
    private LoadingService loadingService;



//
//  @Override
//    public void run(ApplicationArguments args) throws Exception {
//        loadingService.uploadDataAndPersist();
// }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        SpringApplication.run(DashboardApplication.class, args);
        VacationDataLoader vacationDataLoader =new VacationDataLoader();
        vacationDataLoader.loadVacationData();

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
