package com.warehouse.demo.util;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public final class TimeUtil {

    private TimeUtil(){
    }

    public static JobDetail buildJobDetail(final Class classJob){
        return JobBuilder
                .newJob(classJob)
                .withIdentity(classJob.getSimpleName())
                .build();
    }


    public static Trigger buildTrigger(final Class classJob){
        return TriggerBuilder
                .newTrigger()
                .withSchedule()
                .build();
    }
}
