package com.warehouse.demo.util;

import com.warehouse.demo.repository.PianoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimerTask;

public class ChangePianoAvailableTimeTasker extends TimerTask {

    private PianoRepository pianoRepository;

    private TrueOrFalse trueOrFalse;

    private String sku;

    public ChangePianoAvailableTimeTasker(PianoRepository pianoRepository, TrueOrFalse trueOrFalse, String sku) {
        this.pianoRepository = pianoRepository;
        this.trueOrFalse = trueOrFalse;
        this.sku = sku;
    }

    @Override
    public void run() {
        switch (trueOrFalse){
            case TRUE:
                pianoRepository.setAvailablePianoForTrue(getSku());
                break;
            case FALSE:
                pianoRepository.setAvailablePianoForFalse(getSku());
                break;
        }
    }

    public TrueOrFalse getTrueOrFalse() {
        return trueOrFalse;
    }

    public String getSku() {
        return sku;
    }

}
