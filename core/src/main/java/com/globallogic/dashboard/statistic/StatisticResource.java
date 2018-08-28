package com.globallogic.dashboard.statistic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistic")
public class StatisticResource {

    private StatisticFacade statisticFacade;

    public StatisticResource(StatisticFacade statisticFacade) {
        this.statisticFacade = statisticFacade;
    }

    @GetMapping
    public List<StatisticDto> getStatsByYearAndMonth(@RequestParam String year,
                                                     @RequestParam String month,       // months are counted from 0
                                                     @RequestParam String teamId) {

        return statisticFacade.getStatistic(year, month,teamId);
    }
}
