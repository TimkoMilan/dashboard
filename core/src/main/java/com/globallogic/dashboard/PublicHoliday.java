package com.globallogic.dashboard;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource("classpath:publicHoliday.properties")
@ConfigurationProperties(prefix = "holiday")
public class PublicHoliday {
    private Map<String, String> easter;
    private String data;

    public Map<String, String> getEaster() {
        return easter;
    }

    public void setEaster(Map<String, String> easter) {
        this.easter = easter;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "publicHoliday{" +
                "easter=" + easter +
                ", data='" + data + '\'' +
                '}';
    }

}
