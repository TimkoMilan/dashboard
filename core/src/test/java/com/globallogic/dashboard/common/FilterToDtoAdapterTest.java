package com.globallogic.dashboard.common;

import com.globallogic.dashboard.vacation.VacationFilterDto;
import org.junit.Assert;
import org.junit.Test;

public class FilterToDtoAdapterTest {

    @Test
    public void dotest() {
        FilterToDtoAdapter<VacationFilterDto> vacationFilterDtoFilterToDtoAdapter = new FilterToDtoAdapter<>("vacation?memberId=12&teamId=15", VacationFilterDto.class);
        VacationFilterDto dto = vacationFilterDtoFilterToDtoAdapter.getDto();
        Assert.assertEquals(12l, (long)dto.getMemberId());
        Assert.assertEquals(15l, (long)dto.getTeamId());

    }
}