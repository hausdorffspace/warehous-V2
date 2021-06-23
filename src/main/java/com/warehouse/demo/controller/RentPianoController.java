package com.warehouse.demo.controller;

import com.warehouse.demo.exception.AppException;
import com.warehouse.demo.exception.TokenNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.payload.response.PianoResponse;
import com.warehouse.demo.service.PianoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class RentPianoController {

    private PianoService pianoService;

    @Autowired
    public RentPianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    @Bean
    public ModelMapper getObjectMapper() {
        return new ModelMapper();
    }

    //TODO autorization filter,
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/api/rent")
    ResponseEntity<PianoResponse> rentPiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders header) {
        Optional<String> authorization = header.get("Authorization").stream().findFirst();
        //Just wanna see what is inside, delete then
        header.get("Authorization").forEach(System.out::println);
        if (authorization.isEmpty()) {
            throw new TokenNotFoundException();
        } else {
            String token = authorization.get();
            Optional<Piano> rentedPiano = pianoService.rentPiano(rentPianoRequest, token);
            if (rentedPiano.isPresent()) {
                PianoResponse updatedPiano = rentedPiano.stream()
                        .map(piano -> getObjectMapper().map(piano, PianoResponse.class))
                        .findFirst()
                        .get();
                return new ResponseEntity<>(updatedPiano, HttpStatus.OK);
            } else {
                //TODO
                throw new AppException("Awdawd");
            }
        }
    }
}
