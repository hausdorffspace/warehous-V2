package com.warehouse.demo.controller;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.service.PianoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PianoController.class)
@WebAppConfiguration
class PianoControllerTest {

    @MockBean
    private PianoService pianoService;

    @Autowired
    private MockMvc mockMvc;


    //TODO how to deal with security
    @Test
    void getAllPiano() {

        List<Piano> pianosFromDatabase = new ArrayList<>();
        pianosFromDatabase.add(new Piano());
        List<Piano> optionalPianos = pianosFromDatabase;
        when(pianoService.findAll())
                .thenReturn(optionalPianos);

        try {
            this.mockMvc.perform(get("/api/pianos"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$",hasSize(1)))/*
                    .andExpect(jsonPath("$[0].test",is("test")))*/;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}