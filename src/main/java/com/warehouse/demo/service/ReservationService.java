package com.warehouse.demo.service;


import com.warehouse.demo.exception.CannotReservePianoInThisDurationException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.Reservation;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.ReservarionRepository;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import com.warehouse.demo.util.ReadUserFromJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ReservationService {

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    private ReservarionRepository reservarionRepository;

    private PianoRepository pianoRepository;

    @Autowired
    public ReservationService(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, ReservarionRepository reservarionRepository, PianoRepository pianoRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.reservarionRepository = reservarionRepository;
        this.pianoRepository = pianoRepository;
    }

    private ReadUserFromJWT getReadUserFromJWT() {
        return new ReadUserFromJWT(jwtTokenProvider, userRepository);
    }

    public Reservation reservePiano(RentPianoRequest rentPianoRequest, HttpHeaders header) {
        List<Reservation> reservations = reservarionRepository.reservationListOfPianoWithSKU(rentPianoRequest.getSku());
        if (isAvaliable(reservations, rentPianoRequest)) {
            if(rentPianoRequest.getStartDate().compareTo(LocalDate.now())==0){
                return rentPiano(rentPianoRequest,header);
            } else {
                return reservePianoInFuture(rentPianoRequest,header);
            }
        } else {
            throw new CannotReservePianoInThisDurationException("Cannot reserve piano");
        }
    }

    private Reservation rentPiano(RentPianoRequest rentPianoRequest, HttpHeaders header){
        return reservarionRepository.save(Reservation.builder()
                .startReservationDate(rentPianoRequest.getStartDate())
                .endReservationDate(rentPianoRequest.getExpirationDate())
                .piano(pianoRepository.findPianoBySku(rentPianoRequest.getSku()).get())
                .user(getReadUserFromJWT().getUser(header))
                .build()
        );
    }

    private Reservation reservePianoInFuture(RentPianoRequest rentPianoRequest, HttpHeaders header){
        return reservarionRepository.save(Reservation.builder()
                .startReservationDate(rentPianoRequest.getStartDate())
                .endReservationDate(rentPianoRequest.getExpirationDate())
                .piano(pianoRepository.findPianoBySku(rentPianoRequest.getSku()).get())
                .user(getReadUserFromJWT().getUser(header))
                .build()
        );
    }

    private boolean isAvaliable(List<Reservation> reservations, RentPianoRequest rentPianoRequest) {
        return reservations.stream()
                .filter(isDurationCrossing(rentPianoRequest))
                .count() == 0L;
    }

    private Predicate<Reservation> isDurationCrossing(RentPianoRequest rentPianoRequest) {
        return reservation -> isBetweenDuration(rentPianoRequest.getStartDate(), reservation.getStartReservationDate(), reservation.getEndReservationDate()) || isBetweenDuration(rentPianoRequest.getExpirationDate(), reservation.getStartReservationDate(), reservation.getEndReservationDate());
    }

    private boolean isBetweenDuration(LocalDate date1, LocalDate startDuration, LocalDate endDuration) {
        return date1.isAfter(startDuration) && endDuration.isAfter(date1);
    }

}
