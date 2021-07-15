package com.warehouse.demo.controller;


import com.warehouse.demo.exception.NoPianoInDatabaseException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.payload.response.PianoResponse;
import com.warehouse.demo.service.PianoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PianoController {

    private PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    @Bean
    private ModelMapper getObjectMapper() {
        return new ModelMapper();
    }

    //TODO tests
    @GetMapping("/api/pianos")
    ResponseEntity<List<PianoResponse>> getAllPiano() {
        List<Piano> pianos = pianoService.findAll();
        if (pianos.isEmpty()) {
            throw new NoPianoInDatabaseException();
        } else {
            List<PianoResponse> response = pianos.stream()
                    .map(piano -> getObjectMapper().map(piano, PianoResponse.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/api/pianos/available")
    ResponseEntity<List<PianoResponse>> getAllAvailablePiano() {
        return new ResponseEntity<>(pianoService.findAllAvailablePiano().stream()
                .map(p -> getObjectMapper().map(p, PianoResponse.class))
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }


    /*//TODO only admin should be allowed to add the piano
    @PostMapping("/api/addPiano")
    ResponseEntity<?> addPiano(@RequestBody){

        return null;
    }
*/


}
