package com.globallogic.dashboard;

import com.globallogic.dashboard.loader.DataLoader;
import com.globallogic.dashboard.loader.GoogleDataLoader;
import com.globallogic.dashboard.loader.SprintDataLoader;
import com.globallogic.dashboard.loader.SprintLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableScheduling
public class Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public DataLoader dataLoader(GoogleDataLoaderConfigSpring googleDataLoaderConfigSpring) {
        return new GoogleDataLoader(googleDataLoaderConfigSpring);
    }
    @Bean
    public SprintLoader sprintLoader(){
        return new SprintDataLoader();
    }

}
