package br.com.business.agregadorinvestimentos.service;

import br.com.business.agregadorinvestimentos.dtos.UserRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.UserResponseDTO;
import br.com.business.agregadorinvestimentos.mapper.UserMapper;
import br.com.business.agregadorinvestimentos.model.User;
import br.com.business.agregadorinvestimentos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UUID createUser(UserRequestDTO userRequestDTO){

        User entity = UserMapper.toEntity(userRequestDTO);

        User userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public UserResponseDTO getUserById(UUID userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + userId));

        return UserMapper.toResponse(user);
    }

    public List<UserResponseDTO> getAllUsers(){

        List<User> userList = userRepository.findAll();

        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        for (User user : userList) {
            userResponseDTOs.add(UserMapper.toResponse(user));
        }

        return userResponseDTOs;
    }

    public void updateUserById(UUID userId, UserRequestDTO userRequestDTO){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + userId));

        user.setUserName(userRequestDTO.userName());
        user.setEmail(userRequestDTO.email());
        user.setPassword(userRequestDTO.password());

        userRepository.save(user);

    }

    public void deleteUserById(UUID userId){

        boolean existsUser = userRepository.existsById(userId);

        if(existsUser){
            userRepository.deleteById(userId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + userId);
        }

    }



}
