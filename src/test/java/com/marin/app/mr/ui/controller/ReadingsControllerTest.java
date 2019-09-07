package com.marin.app.mr.ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marin.app.mr.service.MeterService;
import com.marin.app.mr.shared.Dto.MeterDto;
import com.marin.app.mr.ui.model.request.MeterReadingRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReadingsControllerTest {

    @MockBean
    MeterService meterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /readings/addresses/Ulica 7/years/2019 - Found")
    void testGetYearlyAggregateReadingFound() throws Exception {

        when(meterService.getYearlyAggregateReading("Ulica 7", 2019)).thenReturn(44);

        mockMvc.perform(get("/readings/addresses/{address}/years/{year}", "Ulica 7", 2019))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

                .andExpect(jsonPath("$.year", is(2019)))
                .andExpect(jsonPath("$.total", is(44)));

    }

    @Test
    @DisplayName("GET /readings/addresses/Wrong Data/years/2019 - Not Found")
    void testGetYearlyAggregateReadingNotFound() throws Exception {

        when(meterService.getYearlyAggregateReading("Wrong Data", 2019)).thenReturn(0);

        mockMvc.perform(get("/readings/addresses/{address}/years/{year}", "Wrong Data", 2019))

                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("GET /readings/addresses/{address}/years/{year}/months - Found")
    void testGetYearlyReadingsFound() throws Exception{

        List<MeterDto> meterDtoList = new ArrayList<>();
        meterDtoList.add(new MeterDto());

        when(meterService.getMonthlyReading("Ulica 7", 2019)).thenReturn(meterDtoList);

        mockMvc.perform(get("/readings/addresses/{address}/years/{year}/months", "Ulica 7", 2019))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    @DisplayName("GET /readings/addresses/{address}/years/{year}/months - Not Found")
    void testGetYearlyReadingsNotFound() throws Exception{

        when(meterService.getMonthlyReading("Ulica 7", 2019)).thenReturn(null);

        mockMvc.perform(get("/readings/addresses/{address}/years/{year}/months", "Ulica 7", 2019))

                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("GET /readings/addresses/{address}/years/{year}/months/{month} - Found")
    void testGetMonthReadingFound() throws Exception{

        when(meterService.getMonthReading("Ulica 7", 2019, "September")).thenReturn(new MeterDto());

        mockMvc.perform(get("/readings/addresses/{address}/years/{year}/months/{month}", "Ulica 7", 2019, "September"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }


    @Test
    @DisplayName("GET /readings/addresses/{address}/years/{year}/months/{month} - Not Found")
    void testGetMonthReadingNotFound() throws Exception{

        when(meterService.getMonthReading("Ulica 7", 2019, "September")).thenReturn(null);

        mockMvc.perform(get("/readings/addresses/{address}/years/{year}/months/{month}", "Ulica 7", 2019, "September"))

                .andExpect(status().isNotFound());

    }


    @Test
    @DisplayName("POST /readings - Success")
    void testPostCreateReadingSuccess() throws Exception{

        MeterReadingRequestModel model = new MeterReadingRequestModel();
        model.setClient("Marin Puizina");
        model.setAddress("Ulica 7");
        model.setYear(2019);
        model.setMonth("September");
        model.setReading(17);

        when(meterService.createReading(new MeterDto())).thenReturn(new MeterDto());

        mockMvc.perform(post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content( asJsonString(model) ))

                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("POST /readings - Not Found")
    void testPostCreateReadingNotFound() throws Exception{

        mockMvc.perform(post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content( asJsonString(null) ))

                .andExpect(status().isBadRequest());

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}