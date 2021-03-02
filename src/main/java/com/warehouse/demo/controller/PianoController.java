package com.warehouse.demo.controller;


import com.warehouse.demo.exception.AppException;
import com.warehouse.demo.exception.NoPianoInDatabaseException;
import com.warehouse.demo.exception.TokenNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.payload.response.PianoResponse;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.service.PianoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class PianoController {

    private PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    @Bean
    private ModelMapper getObjectMapper(){
        return new ModelMapper();
    }

    //TODO tests
    @GetMapping("/api/pianos")
    ResponseEntity<List<PianoResponse>> getAllPiano() {

        Optional<List<Piano>> pianos = pianoService.findAll();
        if (pianos.isEmpty()) {
            throw new NoPianoInDatabaseException();
        } else {
            List<PianoResponse> response = pianos.get().stream()
                    .map(piano -> getObjectMapper().map(piano,PianoResponse.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    /*//TODO only admin should be allowed to add the piano
    @PostMapping("/api/addPiano")
    ResponseEntity<?> addPiano(@RequestBody){

        return null;
    }*/

    @PutMapping("/api/rent")
    ResponseEntity<PianoResponse> rentPiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders header){
        Optional<String> authorization = header.get("Authorization").stream().findFirst();
        if(authorization.isEmpty()){
            throw new TokenNotFoundException();
        } else {
            String token = authorization.get();
            Optional<Piano> rentedPiano = pianoService.rentPiano(rentPianoRequest, token);
            if(rentedPiano.isPresent()){
                PianoResponse updatedPiano = rentedPiano.stream()
                        .map(piano -> getObjectMapper().map(piano, PianoResponse.class))
                        .findFirst()
                        .get();
                return new ResponseEntity<>(updatedPiano,HttpStatus.OK);
            } else {
                //TODO
                throw new AppException("Awdawd");
            }
        }
    }

}
