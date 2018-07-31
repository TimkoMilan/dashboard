package com.globallogic.dashboard.vacation;

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
}
