package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.model.User;
import br.com.business.agregadorinvestimentos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){

            UUID userId = userService.createUser(userRequestDTO);

            log.info("\n✔ User Criado, ID: {}", userId);// Para melhor desempenho ("conteúdo {}", obj)

        //@RequestBody → deve ser o DTO de entrada (UserRequestDTO)
        //ResponseEntity<User> → pode trocar por ResponseEntity<UserResponseDTO> para não retornar dados sensíveis
        //ResponseDTO é para responder, não para receber
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){ // @PathVariable Quando o valor que você precisa vem diretamente na rota da URL, como em GET /users/5.



        return null;
    }

}
