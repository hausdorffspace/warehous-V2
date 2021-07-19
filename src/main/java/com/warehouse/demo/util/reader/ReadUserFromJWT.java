package com.warehouse.demo.util.reader;

import com.warehouse.demo.exception.TokenNotFoundException;
import com.warehouse.demo.exception.UserNotFoundException;
import com.warehouse.demo.model.User;
import com.warehouse.demo.repository.UserRepository;
import com.warehouse.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReadUserFromJWT {

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    public ReadUserFromJWT(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public User getUser(HttpHeaders header) {
        Optional<String> authorization = header.get("Authorization").stream().findFirst();
        if (authorization.isEmpty()) {
            throw new TokenNotFoundException();
        } else {
            String token = authorization.get();
            Optional<User> user = userRepository.findById(jwtTokenProvider.getUserIdFromJWT(token.substring(7)));
            if (user.isEmpty()){
                throw new UserNotFoundException();
            } else {
                return user.get();
            }
        }
    }

}
