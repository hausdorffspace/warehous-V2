package com.warehouse.demo.service;


import com.warehouse.demo.config.AutoWiringSpringBeanJobFactory;
import com.warehouse.demo.util.CheckPianoAvailableAtTheEndOfTheDayJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PianoStatusQuartzService {

    private ApplicationContext applicationContext;

    @Autowired
    public PianoStatusQuartzService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    @Bean
    public JobDetail jobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("description of a job", "few second before midnight will hit to database to check all reservation. If task will find a reservation that ends today, will change piano status to true");
        return JobBuilder.newJob(CheckPianoAvailableAtTheEndOfTheDayJob.class)
                .withIdentity(JobKey.jobKey("planner"))
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(18, 59))
                .build();
    }


}
