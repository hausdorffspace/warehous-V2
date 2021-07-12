package com.warehouse.demo.service;


import com.warehouse.demo.exception.PianoIsRentException;
import com.warehouse.demo.exception.UserNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.User;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.*;

@Service
public class PianoService {

    private PianoRepository pianoRepository;

    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    private JavaMailSender javaMailSender;

    @Value("${app.pathToTemplateEmail}")
    private String path;

    @Autowired
    public PianoService(PianoRepository pianoRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, JavaMailSender javaMailSender) {
        this.pianoRepository = pianoRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.javaMailSender = javaMailSender;
    }

    public List<Piano> findAll() {
        List<Piano> all = pianoRepository.findAll();
        if (all == null){
            return Collections.emptyList();
        } else {
            return all;
        }
    }

    public List<Piano> findAllAvliablePiano() {
        List<Piano> allAvailablePiano = pianoRepository.findAllAvailablePiano();
        if (allAvailablePiano == null) {
            return Collections.emptyList();
        } else {
            return allAvailablePiano;
        }
    }

































    /*public Optional<Piano> rentPiano(RentPianoRequest rentPianoRequest, String token) {
        Long userIdFromJWT = jwtTokenProvider.getUserIdFromJWT(token.substring(7));
        Optional<User> user = userRepository.findById(userIdFromJWT);
        if (user.isEmpty()){
            throw new UserNotFoundException();
        } else {
            Integer isUpdate = pianoRepository.rentPianoWithSKU(user.get(), rentPianoRequest.getSku(), false);
            if (isUpdate.equals(1)){
                Optional<Piano> rentedPiano = pianoRepository.findPianoBySku(rentPianoRequest.getSku());
                sendAReminderEmailOneDayBeforeReturnThePiano(user.get().getEmail(),rentPianoRequest.getPeriodInDay());
                sendsEmailAfterTimeExpiresAndSetPianoAvailableToTrue(rentedPiano.get().getSKU(), rentPianoRequest.getPeriodInDay());
                return rentedPiano;
            }else {
                throw new PianoIsRentException(rentPianoRequest.getSku());
            }
        }
    }



    private void sendsEmailAfterTimeExpiresAndSetPianoAvailableToTrue(String sku,Long periodInDay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pianoRepository.returnPianoToTheWarehouseWithSku(sku);
            }
        },transformDayToSecond(periodInDay)); //set time after expire of time
    }

    private Long transformDayToSecond(Long periodInDay){
        return periodInDay*86400L;
    }

    private void sendAReminderEmailOneDayBeforeReturnThePiano(String email,Long periodInDay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = null;
                try {
                    mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                    mimeMessageHelper.setTo(email);
                    mimeMessageHelper.setSubject("Remaind");
                    mimeMessageHelper.setText(readTemplateFromFile(), true); //can write here a html
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                javaMailSender.send(mimeMessage);
            }
        }, *//*transformDayToSecond(periodInDay-1)*//* 10000L); //calculate the time.
    }

    //TODO
    private String readTemplateFromFile(){
        String result = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader.lines().forEach(stringBuilder::append);
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
