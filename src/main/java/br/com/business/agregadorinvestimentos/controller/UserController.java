package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController// Indica que a Class é um Controller, porém um Controller REST, ele ira tratar requisições HTPP, e retorna respostas no formato Json.
@RequestMapping("/v1/users") // <- prefixo pra todos os endpoints.
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){ // RequestBody transfere dados do tipo Json para objeto

        log.info("✔ Recebida requisição para criar novo usuário.");

        UUID userId = userService.createUser(userRequestDTO);

        log.info("✔ Usuário Criado, ID: {}", userId);// Para melhor desempenho ("conteúdo {}", obj)

        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}") // Indica que um valor variável será esperado nesta parte da URL. O valor que estiver nessa posição será capturado e pode ser acessado através do parâmetro anotado com @PathVariable.
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") UUID userId){ // @PathVariable Quando o valor que você precisa vem diretamente na rota da URL, como em GET /users/5.

        log.info("✔ Recebida requisição para pegar um usuário pelo ID.");

        UserResponseDTO user = userService.getUserById(userId);

        log.info("✔ Usuário Encontrado.");

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){

        log.info("✔ Recebida requisição para pegar todos usuário.");

        List<UserResponseDTO> users = userService.getAllUsers();

        log.info("✔ Usuários Encontrados.");

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserbyId(@PathVariable("userId") UUID userId, @RequestBody UserRequestDTO userRequestDTO){

        log.info("✔ Recebida requisição para Atualizar um usuário pelo ID.");

        userService.updateUserById(userId, userRequestDTO);

        log.info("✔ Usuário Atualizado.");

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") UUID userId){

        log.info("✔ Recebida requisição para Deletar um usuário pelo ID.");

        userService.deleteUserById(userId);

        log.info("✔ Usuário Deletado.");

        return ResponseEntity.noContent().build();
    }

}
