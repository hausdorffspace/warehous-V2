package com.warehouse.demo.service;


import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class PianoStatusQuartzService {

    private final Scheduler sheluder;

    @Autowired
    public PianoStatusQuartzService(Scheduler sheluder) {
        this.sheluder = sheluder;
    }


    //ADD LOGGER
    @PostConstruct
    public void init(){
        try {
            sheluder.start();
        } catch (SchedulerException e){
            System.out.println(e.getMessage());
        }
    }

    @PreDestroy
    public void preDestruct(){
        try {
            sheluder.shutdown();
        } catch (SchedulerException e){
            System.out.println(e.getMessage());
        }
    }


}
