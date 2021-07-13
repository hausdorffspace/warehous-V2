package com.warehouse.demo.service;


import com.warehouse.demo.exception.CanNotCancelReservationException;
import com.warehouse.demo.exception.CannotReservePianoInThisDurationException;
import com.warehouse.demo.model.Reservation;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.ReservarionRepository;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import com.warehouse.demo.util.ReadUserFromJWT;
import com.warehouse.demo.util.TrueOrFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ReservationService {

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    private ReservarionRepository reservarionRepository;

    private PianoRepository pianoRepository;

    private PianoStatusService pianoStatusService;

    @Autowired
    public ReservationService(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, ReservarionRepository reservarionRepository, PianoRepository pianoRepository, PianoStatusService pianoStatusService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.reservarionRepository = reservarionRepository;
        this.pianoRepository = pianoRepository;
        this.pianoStatusService = pianoStatusService;
    }

    @Bean
    private ReadUserFromJWT getReadUserFromJWT() {
        return new ReadUserFromJWT(jwtTokenProvider, userRepository);
    }

    public Reservation reservePiano(RentPianoRequest rentPianoRequest, HttpHeaders header) {
        List<Reservation> reservations = reservarionRepository.reservationListOfPianoWithSKU(rentPianoRequest.getSku());
        if (isAvailable(reservations, rentPianoRequest)) {
            if (rentPianoRequest.getStartDate().compareTo(LocalDate.now()) == 0) {
                return rentPiano(rentPianoRequest, header);
            } else {
                return reservePianoInFuture(rentPianoRequest, header);
            }
        } else {
            throw new CannotReservePianoInThisDurationException("Cannot reserve piano");
        }
    }

    public Reservation deleteReservation(Long reservarionId, HttpHeaders headers) {
        if (reservarionRepository.getOne(reservarionId).getUser().getId() == getReadUserFromJWT().getUser(headers).getId()) {
            Reservation deletedReservation = reservarionRepository.getOne(reservarionId);
            reservarionRepository.deleteById(reservarionId);
            return deletedReservation;
        } else {
            throw new CanNotCancelReservationException("Can't cancel reservation: id=" + reservarionId + " check request");
        }
    }

    public List<Reservation> getAllReservation() {
        List<Reservation> all = reservarionRepository.findAll();
        if (all == null) {
            return Collections.emptyList();
        } else {
            return all;
        }
    }

    public List<Reservation> getAllUserReservations(HttpHeaders headers) {
        List<Reservation> allUserReservation = reservarionRepository.getAllUserReservation(getReadUserFromJWT().getUser(headers).getId());
        if (allUserReservation == null) {
            return Collections.emptyList();
        } else {
            return allUserReservation;
        }
    }

    private Reservation rentPiano(RentPianoRequest rentPianoRequest, HttpHeaders header) {
        pianoRepository.setAvailablePianoForFalse(rentPianoRequest.getSku());
        pianoStatusService.setPianoAvailableWithDate(rentPianoRequest.getExpirationDate(), TrueOrFalse.TRUE, rentPianoRequest.getSku());
        return reservarionRepository.save(Reservation.builder()
                .startReservationDate(LocalDate.now())
                .endReservationDate(rentPianoRequest.getExpirationDate())
                .piano(pianoRepository.findPianoBySku(rentPianoRequest.getSku()).get())
                .user(getReadUserFromJWT().getUser(header))
                .build()
        );
    }

    private Reservation reservePianoInFuture(RentPianoRequest rentPianoRequest, HttpHeaders header) {
        pianoStatusService.setPianoAvailableWithDate(rentPianoRequest.getStartDate(), TrueOrFalse.FALSE, rentPianoRequest.getSku());
        pianoStatusService.setPianoAvailableWithDate(rentPianoRequest.getExpirationDate(), TrueOrFalse.TRUE, rentPianoRequest.getSku());
        return reservarionRepository.save(Reservation.builder()
                .startReservationDate(rentPianoRequest.getStartDate())
                .endReservationDate(rentPianoRequest.getExpirationDate())
                .piano(pianoRepository.findPianoBySku(rentPianoRequest.getSku()).get())
                .user(getReadUserFromJWT().getUser(header))
                .build()
        );
    }

    private boolean isAvailable(List<Reservation> reservations, RentPianoRequest rentPianoRequest) {
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
