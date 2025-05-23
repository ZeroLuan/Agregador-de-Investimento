package br.com.business.agregadorinvestimentos.dtos;

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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "O nome é obrigatório")// Garante que o campo não seja null, vazio ou só espaços.
        String userName,

        @Email(message = "Formato de email inválido")// Valida o formato de e-mail.
        String email,

        @Size(min = 3, message = "A senha deve ter pelo menos 6 caracteres")// Define um tamanho mínimo e/ou máximo para Strings, listas, etc.
        String password
) {}


// DTO de requisição (entrada) utilizado para receber os dados enviados pelo cliente na criação ou atualização de um usuário.
// @RequestBody deve utilizar o DTO de entrada (UserRequestDTO).
// Não se usa RequestEntity<User> — o correto é receber os dados via @RequestBody com o DTO adequado.
// UserRequestDTO é usado apenas para **receber** dados do cliente, não para responder.