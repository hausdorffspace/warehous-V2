package com.warehouse.demo.service;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.User;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@SpringBootTest
class PianoServiceTest {


    @Autowired
    private PianoService pianoService;

    @MockBean
    private PianoRepository pianoRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void shouldReturnAllPianosFromDatabase() {
        //Setup mock repository
        List<Piano> pianosFromDatabase = new ArrayList<>();
        pianosFromDatabase.add(new Piano());
        doReturn(pianosFromDatabase).when(pianoRepository).findAll();

        //Execute the service call
        List<Piano> returnedPianos = pianoService.findAll().get();

        //assert the response
        Assertions.assertNotNull(returnedPianos, "returned piano should not be null");
        Assertions.assertEquals(1, returnedPianos.size());
    }

    /*@Test
    void shouldThrowExceptionWhenCallFindAll(){
        //Setup mock repository
        doReturn(Optional.of(new ArrayList<Piano>())).when(pianoRepository).findAll();

        //Execute the service call
        List<Piano> returnedPianos = pianoService.findAll().get();

        //assert the response
        Assertions.assertNotNull(returnedPianos, "returned piano should not be null");
        Assertions.assertEquals(1, returnedPianos.size());
    }*/

    @Test
    void rentPiano(){
        //Setup mock repository and provider
        when(jwtTokenProvider.getUserIdFromJWT(any())).thenReturn(1L);
        when(pianoRepository.rentPianoWithSKU(any(User.class),eq("QWERTY"),eq(false))).thenReturn(1);
        doReturn(Optional.of(new User())).when(userRepository).findById(any());
        doReturn(Optional.of(new Piano())).when(pianoRepository).findPianoBySku(any());

        //Execute the service call
        Optional<Piano> rentedPiano = pianoService.rentPiano(new RentPianoRequest("QWERTY"),"THIS IS TOKEN");

        //assert the response
        Assertions.assertNotNull(rentedPiano);

    }

}