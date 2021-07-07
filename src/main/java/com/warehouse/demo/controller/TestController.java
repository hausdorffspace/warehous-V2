package com.warehouse.demo.controller;


import com.warehouse.demo.repository.ReservarionRepository;
import com.warehouse.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private ReservarionRepository reservarionRepository;

    @Autowired
    public TestController(ReservarionRepository reservarionRepository) {
        this.reservarionRepository = reservarionRepository;
    }

    @GetMapping("test")
    public ResponseEntity<?> test(){
        reservarionRepository.findAll().forEach(System.out::println);
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
