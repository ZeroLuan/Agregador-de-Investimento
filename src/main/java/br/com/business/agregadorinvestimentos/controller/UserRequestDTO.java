package br.com.business.agregadorinvestimentos.controller;

/*
    Por que usar record?
    Porque ela:
    Cria automaticamente:

    Construtor, Getters, equals(), hashCode() e toString()
    É mais limpa e enxuta
    Não permite mudanças nos campos (são "final" por padrão → imutabilidade)

    Ideal para DTOs
    DTOs são só "transportadores de dados", não têm lógica nem precisam ser alterados.
    Os dados vêm do front e vão direto para backend ou vice-versa.
    Uma vez criado, o DTO não precisa ser modificado → por isso, imutabilidade (característica do record).
*/

//Request para Entrada
public record UserRequestDTO(String userName, String email, String password) {
}

// DTO de requisição (entrada) utilizado para receber os dados enviados pelo cliente na criação ou atualização de um usuário.
// @RequestBody deve utilizar o DTO de entrada (UserRequestDTO).
// Não se usa RequestEntity<User> — o correto é receber os dados via @RequestBody com o DTO adequado.
// UserRequestDTO é usado apenas para **receber** dados do cliente, não para responder.