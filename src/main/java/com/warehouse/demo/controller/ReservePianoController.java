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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ReservationResponse> reservePiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(modelMapper.map(reservationService.reservePiano(rentPianoRequest, headers), ReservationResponse.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservation() {
        return new ResponseEntity<>(reservationService.getAllReservation().stream()
                .map(reservation -> modelMapper.map(reservation, ReservationResponse.class))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/api/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllUserReservation(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(reservationService.getAllUserReservations(headers).stream()
                .map(reservation -> modelMapper.map(reservation, ReservationResponse.class))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    //Maybe change return type... ReservationResponse for nothing?? and return 204
    @DeleteMapping("/api/reservation/delete")
    public ResponseEntity<ReservationResponse> deleteReservation(Long reservationId, @RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(modelMapper.map(reservationService.deleteReservation(reservationId, headers), ReservationResponse.class), HttpStatus.OK);
    }
}
