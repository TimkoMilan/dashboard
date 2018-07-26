package com.globallogic.dashboard;

import com.globallogic.dashboard.loader.GoogleDataLoaderConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@PropertySource("classpath:config.properties")
@Component
public class GoogleDataLoaderConfigSpring extends GoogleDataLoaderConfig {
}
