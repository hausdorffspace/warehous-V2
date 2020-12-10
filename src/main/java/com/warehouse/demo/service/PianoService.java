package com.warehouse.demo.service;


import com.warehouse.demo.exception.PianoNotFoundException;
import com.warehouse.demo.exception.UserNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.User;
import com.warehouse.demo.payload.request.RentPianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PianoService {

    private PianoRepository pianoRepository;

    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public PianoService(PianoRepository pianoRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.pianoRepository = pianoRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public Optional<List<Piano>> findAll(){
        return Optional.ofNullable(pianoRepository.findAll());
    }

    public Optional<Piano> rentPiano(RentPianoRequest rentPianoRequest, String token){
        Long userIdFromJWT = jwtTokenProvider.getUserIdFromJWT(token.substring(7));
        Optional<User> user = userRepository.findById(userIdFromJWT);
        if (user.isEmpty()){
            throw new UserNotFoundException();
        } else {
            Integer isUpdate = pianoRepository.rentPianoWithSKU(user.get(), rentPianoRequest.getSku(), false);
            if (isUpdate.equals(1)){
                return pianoRepository.findPianoBySku(rentPianoRequest.getSku());
            }else {
                throw new PianoNotFoundException(rentPianoRequest.getSku());
            }
        }
    }
}
