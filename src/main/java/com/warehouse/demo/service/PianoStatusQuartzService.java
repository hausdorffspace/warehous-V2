package com.warehouse.demo.service;


import com.warehouse.demo.util.TimeUtil;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class PianoStatusQuartzService {

    private final Scheduler scheduler;

    @Autowired
    public PianoStatusQuartzService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass){
        try {
            scheduler.scheduleJob(TimeUtil.buildJobDetail(jobClass),TimeUtil.buildTrigger(jobClass));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //ADD LOGGER
    @PostConstruct
    public void init(){
        try {
            scheduler.start();
        } catch (SchedulerException e){
            System.out.println(e.getMessage());
        }
    }

    @PreDestroy
    public void preDestruct(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e){
            System.out.println(e.getMessage());
        }
    }


}
