package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;

public final class VacationUtil {
    private VacationUtil(){

    }

    public static VacationDto convertToDto (Vacation vacation){
        VacationDto vacationDto = new VacationDto();

        vacationDto.setFrom(vacation.getStart());
        vacationDto.setTo(vacation.getEnd());
        vacationDto.setHalfDay(vacation.isHalfDay());
        vacationDto.setName(vacation.getMember().getName());

        return vacationDto;
    }
    public static VacationDto converToDoFromData (VacationData vacationData){
        VacationDto vacationDto = new VacationDto();
        vacationDto.setName(vacationData.getName());
        vacationDto.setFrom(vacationData.getFrom());
        vacationDto.setTo(vacationData.getTo());
        vacationDto.setHalfDay(vacationData.isHalfDay());
        return vacationDto;
    }
}
