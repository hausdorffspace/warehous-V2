package com.warehouse.demo.util;

import com.warehouse.demo.model.Reservation;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.ReservarionRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CheckPianoAvailableAtStartOfTheDayJob implements Job {

    private PianoRepository pianoRepository;

    private ReservarionRepository reservarionRepository;

    @Autowired
    public CheckPianoAvailableAtStartOfTheDayJob(PianoRepository pianoRepository, ReservarionRepository reservarionRepository) {
        this.pianoRepository = pianoRepository;
        this.reservarionRepository = reservarionRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Reservation> allReservationsThatBeginToday = reservarionRepository.findAll(Example.of(Reservation.builder().startReservationDate(LocalDate.now()).build()));
        if (!allReservationsThatBeginToday.isEmpty()) {
            List<Long> pianosId = allReservationsThatBeginToday.stream()
                    .map(reservation -> reservation.getPiano().getId())
                    .collect(Collectors.toList());
            pianoRepository.setAvailablePianoForTrue(pianosId);
        }
        System.out.println("heheheh");
    }
}
