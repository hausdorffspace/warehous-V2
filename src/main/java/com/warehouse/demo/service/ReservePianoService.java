package com.warehouse.demo.service;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.repository.ReservationResposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservePianoService {

    private ReservationResposiotry reservationResposiotry;

    @Autowired
    public ReservePianoService(ReservationResposiotry reservationResposiotry) {
        this.reservationResposiotry = reservationResposiotry;
    }

    List<Piano>
}