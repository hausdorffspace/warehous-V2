package com.warehouse.demo.util;

import io.swagger.annotations.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Timer;
import java.util.TreeMap;

@Component
@Scope(name = "singleton", description = "sef")
public class TimerBox {

    private Map<Long, Timer> timeTaskerList = new TreeMap<>();

    public void addTimer(Long idReservation, Timer timer){
        timeTaskerList.put(idReservation,timer);
    }

    public void shutDownTasker(Long idReservation){
        timeTaskerList.get(idReservation).cancel();
    }

}
