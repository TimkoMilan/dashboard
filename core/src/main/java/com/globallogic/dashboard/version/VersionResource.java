package com.globallogic.dashboard.version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource("classpath:/application.properties")
@RestController
@RequestMapping("version")
public class VersionResource {
    @Value("${server.version}") private String version;
    @Value("${server.version.releaseDate}")private String releaseDate;

@GetMapping
public VersionDto version(){

    VersionDto dto = new VersionDto();
    dto.setVersion(version);
    dto.setReleaseDate(releaseDate);
    return dto;

    }
}
