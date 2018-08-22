package com.globallogic.dashboard.publicHoliday;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workingfont")
public class PublicHolidayResource {

    private PublicHolidayLoader publicHolidayLoader;

    public PublicHolidayResource(PublicHolidayLoader publicHolidayLoader) {
        this.publicHolidayLoader = publicHolidayLoader;
    }

    @GetMapping
    public int getpublicHoliday(@RequestParam int year,@RequestParam int month){
        return publicHolidayLoader.getWorkingFond(year,month);
    }
}
