package com.warehouse.demo.controller;


import com.warehouse.demo.model.Piano;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.payload.response.ReservationResponse;
import com.warehouse.demo.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReservePianoController {

    private ReservationService reservationService;

    private ModelMapper modelMapper;

    @Autowired
    public ReservePianoController(ReservationService reservationService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }

    /*@PreAuthorize("hasRole('ROLE_USER')")*/
    @PutMapping("/api/rent")
    public ResponseEntity<ReservationResponse> reservePiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(modelMapper.map(reservationService.reservePiano(rentPianoRequest,headers),ReservationResponse.class),HttpStatus.OK);
    }
}
