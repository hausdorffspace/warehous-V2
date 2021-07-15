package com.warehouse.demo.service;

import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.ReservarionRepository;
import com.warehouse.demo.util.ChangePianoAvailableTimeTasker;
import com.warehouse.demo.util.TimerBox;
import com.warehouse.demo.util.TrueOrFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

@Service
public class PianoStatusService {

    private PianoRepository pianoRepository;

    private TimerBox timerBox;

    @Autowired
    public PianoStatusService(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }

    public void setPianoAvailableWithDate(LocalDate localDate, TrueOrFalse trueOrFalse, String sku, Long idReservation) {
        Timer timer = new Timer();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        timer.schedule(new ChangePianoAvailableTimeTasker(pianoRepository, trueOrFalse, sku), date);
        timerBox.addTimer(idReservation,timer);
    }

    public void cancelTimerTaskThread(Long reservationId) {
        timerBox.shutDownTasker(reservationId);
    }
}
