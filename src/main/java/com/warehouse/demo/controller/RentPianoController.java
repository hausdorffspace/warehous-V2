package com.warehouse.demo.controller;

import com.warehouse.demo.exception.AppException;
import com.warehouse.demo.exception.TokenNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.payload.response.PianoResponse;
import com.warehouse.demo.service.PianoService;
import com.warehouse.demo.service.RentPianoService;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RentPianoController {

    private PianoService pianoService;

    private RentPianoService rentPianoService;

    @Autowired
    public RentPianoController(PianoService pianoService,RentPianoService rentPianoService) {
        this.pianoService = pianoService;
        this.rentPianoService = rentPianoService;    }

    @Bean
    public ModelMapper getObjectMapper() {
        return new ModelMapper();
    }

    //TODO autorization filter,
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/api/rent")
    ResponseEntity<PianoResponse> rentPiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders header) {
        return new ResponseEntity<>(getObjectMapper().map(rentPianoService.rentPiano(rentPianoRequest,header),PianoResponse.class),HttpStatus.OK);
    }
}
