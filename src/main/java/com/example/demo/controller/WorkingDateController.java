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

    @GetMapping("/ending-date")
    public ResponseEntity<GetEndingDateRespDTO> getEndingDate(@RequestBody GetEndingDateDTO dateDTO) {
        return new ResponseEntity<>(workingDateService.getEndingDate(dateDTO), HttpStatus.OK);
    }

    @PutMapping("/update-holidays")
    public ResponseEntity<List<LocalDate>> addPublicHoliday(@RequestBody PublicHolidayDTO dto){
        return new ResponseEntity<>(workingDateService.addPublicHoliday(dto), HttpStatus.OK);

    }
}
