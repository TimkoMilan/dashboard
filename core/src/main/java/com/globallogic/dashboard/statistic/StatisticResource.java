package com.globallogic.dashboard.statistic;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("statistic")
public class StatisticResource {

    private StatisticFacade statisticFacade;

    public StatisticResource(StatisticFacade statisticFacade) {
        this.statisticFacade = statisticFacade;
    }

    @GetMapping
    public List<StatisticDto> getStatsByYearAndMonth(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                 Date startDate,
                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                 Date endDate,       // months are counted from 0
                                                     @RequestParam String teamId) {

        return statisticFacade.getStatisticByDateRange(startDate, endDate,teamId);
    }
}
