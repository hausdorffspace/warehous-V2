package com.warehouse.demo.controller;


import com.warehouse.demo.model.Piano;
import com.warehouse.demo.service.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PianoController {

    private PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    //TODO test, response class
    @GetMapping("/api/Pianos")
    ResponseEntity<List<Piano>> getAllPiano(){
        return new ResponseEntity<>(pianoService.findAll(), HttpStatus.OK);
    }

}
