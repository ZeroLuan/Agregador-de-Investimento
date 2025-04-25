package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Qaundo eu uso o Anottaion RestController digo o siguinte: “Essa classe vai responder requisições web e os métodos dela vão retornar dados, não páginas HTML.”
@RestController
@RequestMapping("/v1/users")// Ele define que todos os EndPoints começarão com /v1/users ou seja, define o prefixo da URL a todos os Users
public class UserController {

    // Isso é um EndPoint, basicamente é uma URL + um verbo HTTP(GET,POST,PUT,DELETE) que representa uma função de sua API
    @PostMapping // Completa apartir do prefixo comum que é o /v1/users, ficando /v1/users/hello
    public ResponseEntity<User> createUser(@RequestBody String body){// @RequestBody ele pega a requisição que vem como JSON e transforma em um objeto Java



        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){ // @PathVariable Quando o valor que você precisa vem diretamente na rota da URL, como em GET /users/5.



        return null;
    }

}
