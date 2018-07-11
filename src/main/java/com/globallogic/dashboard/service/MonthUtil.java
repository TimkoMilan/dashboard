package com.globallogic.dashboard.service;

import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MonthUtil {
    private static final Logger log = LoggerFactory.getLogger(VacationDataLoader.class);

    private MonthUtil() {
        //as this is util
    }

    public static Set<VacationDataLoader.RangeMeta> extractMothRangesForObjects(List<Object> objects) {
        int from = 0, i = 0;
        Set<VacationDataLoader.RangeMeta> rangeMetas = new HashSet<>();
        String monthPrev = null;
        for (Object object : objects) {
            if (!object.toString().isEmpty()) {
                if (monthPrev == null) {
                    monthPrev = object.toString();
                    from = i;
                } else {
                    rangeMetas.add(new VacationDataLoader.RangeMeta(monthPrev, Range.between(from, i)));
                    monthPrev = object.toString();
                    log.info("Payload from parsing:{}", object.toString());
                    from = i;
                }
            }
            i++;
        }
        rangeMetas.add(new VacationDataLoader.RangeMeta(monthPrev, Range.between(from, i+31)));
        return rangeMetas;
    }

    public static String getMonthForCellId(Set<VacationDataLoader.RangeMeta> rangeMetas, Integer cellId) {
        for (VacationDataLoader.RangeMeta rangeMeta : rangeMetas) {
            if (rangeMeta.getRange().contains(cellId)) {
                return rangeMeta.getMonthName();
            }
        }
        throw new RuntimeException("No month found for cell id: " + cellId); //todo create custom service level exception
    }


}
