package com.warehouse.demo.util;

import org.quartz.*;

public final class TimeUtil {

    private TimeUtil(){
    }

    public static JobDetail buildJobDetail(final Class classJob){
        return JobBuilder
                .newJob(classJob)
                .withIdentity(classJob.getSimpleName())
                .build();
    }


    public static Trigger buildTrigger(final Class classJob, int hour, int minutes){
        return TriggerBuilder
                .newTrigger()
                .withIdentity(classJob.getSimpleName())
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hour,minutes))
                .startNow()
                .build();
    }

}
