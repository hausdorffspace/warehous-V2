package com.warehouse.demo.service;

import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.util.ChangePianoAvailableTimeTasker;
import com.warehouse.demo.util.TrueOrFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

@Service
public class PianoStatusService {

    private PianoRepository pianoRepository;

    @Autowired
    public PianoStatusService(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }

    public void setPianoAvailableWithDate(LocalDate localDate, TrueOrFalse trueOrFalse, String sku) {
        Timer timer = new Timer();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        timer.schedule(new ChangePianoAvailableTimeTasker(pianoRepository, trueOrFalse, sku), date);
    }

    /*
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
