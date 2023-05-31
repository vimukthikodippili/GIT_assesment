package com.example.demo.controller;


import com.example.demo.dto.GetEndingDateDTO;
import com.example.demo.dto.GetEndingDateRespDTO;
import com.example.demo.dto.PublicHolidayDTO;
import com.example.demo.service.WorkingDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/days")
public class WorkingDateController {

    private final WorkingDateService workingDateService;

    public WorkingDateController(WorkingDateService workingDateService) {
        this.workingDateService = workingDateService;
    }
/**
 * Get the ending date
 @param dateDTO the GetEndingDateDTO object containing the starting date and working days
 @return the ResponseEntity with the GetEndingDateRespDTO object representing the ending date

 * */
    @GetMapping("/ending-date")
    public ResponseEntity<GetEndingDateRespDTO> getEndingDate(@RequestBody GetEndingDateDTO dateDTO) {
        return new ResponseEntity<>(workingDateService.getEndingDate(dateDTO), HttpStatus.OK);
    }
    /**
     * Add a public holiday to the list of holidays.
     *
     * @param dto the PublicHolidayDTO object containing the public holiday date
     * @return the ResponseEntity with the list of LocalDate representing the updated list of public holidays
     */
    @PutMapping("/update-holidays")
    public ResponseEntity<List<LocalDate>> addPublicHoliday(@RequestBody PublicHolidayDTO dto){
        return new ResponseEntity<>(workingDateService.addPublicHoliday(dto), HttpStatus.OK);

    }
}
