package com.example.demo.service.impl;


import com.example.demo.dto.GetEndingDateDTO;
import com.example.demo.dto.GetEndingDateRespDTO;
import com.example.demo.dto.PublicHolidayDTO;
import com.example.demo.exception.JITBadRequestException;
import com.example.demo.service.WorkingDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WorkingDateServiceImpl implements WorkingDateService {
    List<LocalDate> publicHolidays = new ArrayList<>();

    @Override
    public GetEndingDateRespDTO getEndingDate(GetEndingDateDTO dto) {
        try {
            LocalDate relevantDate = dto.getStartingDay();
            int remainingWorkingDays = dto.getWorkingDays();
            while (remainingWorkingDays > 0) {
                relevantDate = relevantDate.plusDays(1);
                //check if the relevantDay is a public holiday or a not a workingDay
                if (isBusinessDay(relevantDate) && !isPublicHoliday(relevantDate)) remainingWorkingDays--;
            }
            return GetEndingDateRespDTO.builder()
                    .endingDay(relevantDate)
                    .build();
        }catch (Exception exception){
            throw new JITBadRequestException("getting relevant Date failed.!!");
        }
    }

    @Override
    public List<LocalDate> addPublicHoliday(PublicHolidayDTO dto) {
        if (publicHolidays.contains(dto.getPublicHoliday()))
            throw new JITBadRequestException("Already added to the holiday list");
        publicHolidays.add(dto.getPublicHoliday());
        return publicHolidays;
    }

    public boolean isBusinessDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    public boolean isPublicHoliday(LocalDate date) {
        return publicHolidays.contains(date);
    }
}
