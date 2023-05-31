package com.example.demo;

import com.example.demo.controller.WorkingDateController;
import com.example.demo.dto.GetEndingDateDTO;
import com.example.demo.dto.GetEndingDateRespDTO;
import com.example.demo.dto.PublicHolidayDTO;
import com.example.demo.service.WorkingDateService;
import com.example.demo.service.impl.WorkingDateServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class WorkingDateControllerTest {
    private WorkingDateServiceImpl underTest;
    private WorkingDateController workingDateController;
    private WorkingDateService workingDateService;
    @Mock
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable= MockitoAnnotations.openMocks(this);


    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();

    }
    @Test
    public void testGetEndingDate() {
        GetEndingDateDTO dto = GetEndingDateDTO.builder()
                .startingDay(LocalDate.of(2023, 5, 31))
                .workingDays(1)
                .build();

        GetEndingDateRespDTO expectedResponse = GetEndingDateRespDTO.builder()
                .endingDay(LocalDate.of(2023, 5, 31))
                .build();

        Mockito.when(workingDateService.getEndingDate(dto)).thenReturn(expectedResponse);

        ResponseEntity<GetEndingDateRespDTO> responseEntity = workingDateController.getEndingDate(dto);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedResponse, responseEntity.getBody());
    }
    @Test
    public void testAddPublicHoliday() {
        PublicHolidayDTO dto = PublicHolidayDTO.builder()
                .publicHoliday(LocalDate.of(2023, 5, 20))
                .build();

        List<LocalDate> expectedPublicHolidays = Collections.singletonList(LocalDate.of(2023, 5, 20));

        Mockito.when(workingDateService.addPublicHoliday(dto)).thenReturn(expectedPublicHolidays);

        ResponseEntity<List<LocalDate>> responseEntity = workingDateController.addPublicHoliday(dto);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedPublicHolidays, responseEntity.getBody());
    }
}
