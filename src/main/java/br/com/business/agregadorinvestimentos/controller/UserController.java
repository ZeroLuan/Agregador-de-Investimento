package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.dtos.AccountRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.AccountResponseDTO;
import br.com.business.agregadorinvestimentos.dtos.UserRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.UserResponseDTO;
import br.com.business.agregadorinvestimentos.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController// Indica que a Class é um Controller, porém um Controller REST, ele ira tratar requisições, e retorna respostas no formato Json.
@RequestMapping("/v1/users") // <- Prefixo pra todos os endpoints.
@Slf4j
public class UserController {

    // O IntelliJ sugere usar injeção via construtor, que é mais segura e testável, ao invés de @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){ // RequestBody transfere dados do tipo Json para objeto, @Valid Validação em camada de apresentação ou de entrada

        log.info("✔ Recebida requisição para criar novo usuário.");

        UUID userId = userService.createUser(userRequestDTO);

        log.info("✔ Usuário Criado, ID: {}", userId);// Para melhor desempenho ("conteúdo {}", obj)

        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();//ResponseEntity vai retornar a situação nmr e vai gerar o URI no cabeçalho
    }

    @GetMapping("/{userId}") //  Indica que um valor variável será esperado nesta parte da URL. O valor que estiver nessa posição será capturado e pode ser acessado através do parâmetro anotado com @PathVariable.
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") UUID userId){ // <- @PathVariable Quando o valor que você precisa vem diretamente na rota da URL, como em GET /users/5.

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
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") UUID userId, @RequestBody UserRequestDTO userRequestDTO){

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

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId, @RequestBody AccountRequestDTO accountRequestDTO){

        log.info("✔ Recebida requisição para criar uma conta usando id do Usuário e DTO de account.");

        userService.createAccount(userId, accountRequestDTO);

        log.info("✔ Conta Criada.");

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts(@PathVariable("userId") String userId){

        log.info("✔ Recebida requisição para pegar uma conta pelo ID.");

        List<AccountResponseDTO> account = userService.getAllAccounts(userId);

        log.info("✔ Conta Encontrada.");

        return ResponseEntity.ok(account);
    }

}
