package com.warehouse.demo.service;


import com.warehouse.demo.model.Piano;
import com.warehouse.demo.repository.PianoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PianoService {

    private PianoRepository pianoRepository;

    @Autowired
    public PianoService(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }

    public List<Piano> findAll(){
        return pianoRepository.findAll();
    }
}
