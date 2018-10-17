package com.globallogic.dashboard.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("version")
public class versionResource {

@GetMapping
public VersionDto version(){
    VersionDto versionDto = new VersionDto();
    versionDto.setReleaseDate("3.10.2018");
    versionDto.setVersion("1.10");
    return versionDto;
}
}
