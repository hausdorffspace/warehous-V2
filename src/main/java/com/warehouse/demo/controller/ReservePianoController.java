package com.warehouse.demo.controller;


import com.warehouse.demo.model.Piano;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.service.ReservationService;
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

    @Autowired
    public ReservePianoController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /*@PreAuthorize("hasRole('ROLE_USER')")*/
    @PutMapping("/api/rent")
    public ResponseEntity<Piano> reservePiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(reservationService.reservePiano(rentPianoRequest,headers),HttpStatus.OK);
    }
}
