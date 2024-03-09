package com.info902.projet.service;

import com.info902.projet.controller.request.RegisterRequest;
import com.info902.projet.model.User;
import com.info902.projet.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(RegisterRequest registerRequest){

        Optional<User> user = userRepository.findByPseudo(registerRequest.getPseudo());

        if(user.isEmpty()){
            User newUser = User.builder()
                    .pseudo(registerRequest.getPseudo())
                    .password(registerRequest.getPassword())
                    .build();
            userRepository.save(newUser);
            return newUser;
        } else {
            return null;
        }

    }

    public User loginUser(RegisterRequest registerRequest){
        Optional<User> user = userRepository.findByPseudoAndPassword(registerRequest.getPseudo(), registerRequest.getPassword());
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}
