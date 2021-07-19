package com.warehouse.demo.controller;


import com.warehouse.demo.payload.request.DeleteReservationRequest;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.payload.response.ReservationResponse;
import com.warehouse.demo.service.ReservationService;
import com.warehouse.demo.util.mapper.MapReservationToReservationResponse;
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

    private MapReservationToReservationResponse mapReservationToReservationResponse;

    @Autowired
    public ReservePianoController(ReservationService reservationService, MapReservationToReservationResponse mapReservationToReservationResponse) {
        this.reservationService = reservationService;
        this.mapReservationToReservationResponse = mapReservationToReservationResponse;
    }

    /*@PreAuthorize("hasRole('ROLE_USER')")*/
    @PutMapping("/api/rent")
    public ResponseEntity<ReservationResponse> reservePiano(@RequestBody @Valid RentPianoRequest rentPianoRequest, @RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(mapReservationToReservationResponse.map(reservationService.reservePiano(rentPianoRequest, headers)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservation() {
        List<ReservationResponse> mappedReservations = reservationService.getAllReservation().stream()
                .map(mapReservationToReservationResponse::map)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mappedReservations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/api/user/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllUserReservation(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(reservationService.getAllUserReservations(headers).stream()
                .map(mapReservationToReservationResponse::map)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    //TODO admin should also have privilage to delete reservation
    @DeleteMapping("/api/reservation/delete")
    public ResponseEntity<?> deleteReservation(@RequestBody DeleteReservationRequest deleteReservationRequest, @RequestHeader HttpHeaders headers) {
        reservationService.deleteReservation(deleteReservationRequest, headers);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
