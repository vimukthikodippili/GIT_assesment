package com.example.demo.service;



import com.example.demo.dto.GetEndingDateDTO;
import com.example.demo.dto.GetEndingDateRespDTO;
import com.example.demo.dto.PublicHolidayDTO;

import java.time.LocalDate;
import java.util.List;

public interface WorkingDateService {
    /**
     * Get the ending date based on the starting date and number of working days.
     *
     * @param dto the GetEndingDateDTO object containing the starting date and working days
     * @return the GetEndingDateRespDTO object representing the ending date
     */
    GetEndingDateRespDTO getEndingDate(GetEndingDateDTO dto);

    /**
     * Add a public holiday to the list of holidays.
     *
     * @param dto the PublicHolidayDTO object containing the public holiday date
     * @return the updated list of public holidays as a List of LocalDate
     */
    List<LocalDate> addPublicHoliday(PublicHolidayDTO dto);


}
