package com.example.demo;

import com.example.demo.dto.GetEndingDateDTO;
import com.example.demo.dto.GetEndingDateRespDTO;
import com.example.demo.dto.PublicHolidayDTO;
import com.example.demo.exception.JITBadRequestException;
import com.example.demo.service.WorkingDateService;
import com.example.demo.service.impl.WorkingDateServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkingDateServiceImplTest {
    private WorkingDateServiceImpl underTest;
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
    void getEndingDate(){
        LocalDate startingDate = LocalDate.of(2023, 5, 31);
        int workingDays = 10;


        Mockito.when(underTest.isBusinessDay(Mockito.any(LocalDate.class))).thenReturn(false);
        Mockito.when(underTest.isPublicHoliday(Mockito.any(LocalDate.class))).thenReturn(true);


        GetEndingDateDTO dto = GetEndingDateDTO.builder()
                .startingDay(startingDate)
                .workingDays(workingDays)
                .build();
        GetEndingDateRespDTO result = underTest.getEndingDate(dto);


        LocalDate expectedEndingDate = LocalDate.of(2023, 5, 20);
        Assertions.assertEquals(expectedEndingDate, result.getEndingDay());

    }
    @Test
    void addPublicHoliday(){
        LocalDate publicHoliday = LocalDate.of(2023, 12, 25);
        PublicHolidayDTO dto = PublicHolidayDTO.builder()
                .publicHoliday(publicHoliday)
                .build();


        List<LocalDate> publicHolidays = new ArrayList<>();
        Mockito.when(workingDateService.addPublicHoliday(Mockito.any(PublicHolidayDTO.class))).thenReturn(publicHolidays);


        List<LocalDate> result = underTest.addPublicHoliday(dto);


        Assertions.assertTrue(result.contains(publicHoliday));

    }
    @Test
    void isPublicHoliday(){
        LocalDate existingHoliday = LocalDate.of(2023, 12, 25);
        PublicHolidayDTO dto = PublicHolidayDTO.builder()
                .publicHoliday(existingHoliday)
                .build();


        List<LocalDate> publicHolidays = new ArrayList<>();
        publicHolidays.add(existingHoliday);
        Mockito.when(workingDateService.addPublicHoliday(Mockito.any(PublicHolidayDTO.class))).thenReturn(publicHolidays);


        Assertions.assertThrows(JITBadRequestException.class, () -> {
            underTest.addPublicHoliday(dto);
        });


    }

    /**
     * I haven't done j unit test before so this is my first time

     */
}
