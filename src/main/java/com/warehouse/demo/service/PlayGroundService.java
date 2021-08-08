package com.warehouse.demo.service;


import com.warehouse.demo.util.CheckPianoAvailableAtStartOfTheDayJob;
import com.warehouse.demo.util.CheckPianoAvailableAtTheEndOfTheDayJob;
import com.warehouse.demo.util.BeforeOrAfterMidnight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PlayGroundService {

    private PianoStatusQuartzService pianoStatusQuartzService;

    @Autowired
    public PlayGroundService(PianoStatusQuartzService pianoStatusQuartzService) {
        this.pianoStatusQuartzService = pianoStatusQuartzService;
    }

    /**
     * init work of quartz when the server start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void runQuartzJob() {
        pianoStatusQuartzService.schedule(CheckPianoAvailableAtTheEndOfTheDayJob.class, BeforeOrAfterMidnight.AFTER);
        pianoStatusQuartzService.schedule(CheckPianoAvailableAtStartOfTheDayJob.class,BeforeOrAfterMidnight.BEFORE);
    }
}
