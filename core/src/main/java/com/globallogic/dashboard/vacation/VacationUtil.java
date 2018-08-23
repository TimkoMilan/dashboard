package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;

import java.util.ArrayList;
import java.util.List;

public final class VacationUtil {
    private VacationUtil() {

    }

    static VacationDto convertToDto(Vacation vacation) {
        VacationDto vacationDto = new VacationDto();

        vacationDto.setFrom(vacation.getStart());
        vacationDto.setTo(vacation.getEnd());
        vacationDto.setHalfDay(vacation.isHalfDay());
        if (vacation.getMember() != null) {
            vacationDto.setName(vacation.getMember().getName());
        } else {
            return vacationDto;
        }
        return vacationDto;
    }

    static VacationDto converToDoFromData(VacationData vacationData) {
        VacationDto vacationDto = new VacationDto();

        vacationDto.setName(vacationData.getName());
        vacationDto.setFrom(vacationData.getFrom());
        vacationDto.setTo(vacationData.getTo());
        vacationDto.setHalfDay(vacationData.isHalfDay());
        return vacationDto;
    }

    static List<VacationDto> convertToListDto(Iterable<Vacation> vacations) {
        List<VacationDto> vacationDtos = new ArrayList<>();
        for (Vacation vacation : vacations) {
            vacationDtos.add(convertToDto(vacation));
        }
        return vacationDtos;
    }
}
