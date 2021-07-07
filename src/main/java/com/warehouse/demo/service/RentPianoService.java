package com.warehouse.demo.service;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.User;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import com.warehouse.demo.util.ReadUserFromJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class RentPianoService {

    private PianoRepository pianoRepository;

    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    private JavaMailSender javaMailSender;

    @Value("${app.pathToTemplateEmail}")
    private String path;

    @Autowired
    public RentPianoService(PianoRepository pianoRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, JavaMailSender javaMailSender) {
        this.pianoRepository = pianoRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.javaMailSender = javaMailSender;
    }

    @Bean
    private ReadUserFromJWT getInstanceOfReadUserFromJWT() {
        return new ReadUserFromJWT(jwtTokenProvider, userRepository);
    }

    public Piano rentPiano(RentPianoRequest rentPianoRequest, HttpHeaders header) {
        User userFromJWT = getInstanceOfReadUserFromJWT().getUser(header);
        Integer integer = pianoRepository.rentPianoWithSKU(userFromJWT, rentPianoRequest.getSku(), false);


        return new Piano();
    }
































   /* public Optional<Piano> rentPiano(RentPianoRequest rentPianoRequest, String token) {
       return Optional.empty();
    }


    //TODO
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
