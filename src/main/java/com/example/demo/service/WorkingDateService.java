package com.example.demo.service;



import com.example.demo.dto.GetEndingDateDTO;
import com.example.demo.dto.GetEndingDateRespDTO;
import com.example.demo.dto.PublicHolidayDTO;

import java.time.LocalDate;
import java.util.List;

public interface WorkingDateService {
    GetEndingDateRespDTO getEndingDate(GetEndingDateDTO dto);

    List<LocalDate> addPublicHoliday(PublicHolidayDTO dto);


}
