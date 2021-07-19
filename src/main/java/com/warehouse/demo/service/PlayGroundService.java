package com.warehouse.demo.service;


import com.warehouse.demo.util.CheckPianoAvailableAtTheEndOfTheDayJob;
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

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        pianoStatusQuartzService.schedule(CheckPianoAvailableAtTheEndOfTheDayJob.class);
    }
}
