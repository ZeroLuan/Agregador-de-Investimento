package br.com.business.agregadorinvestimentos.service;

import br.com.business.agregadorinvestimentos.controller.UserRequestDTO;
import br.com.business.agregadorinvestimentos.model.User;
import br.com.business.agregadorinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UUID createUser(UserRequestDTO userRequestDTO){

        User entity = new User(
                userRequestDTO.userName(),
                userRequestDTO.email(),
                userRequestDTO.password()
                );

        User userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

}
