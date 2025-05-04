package br.com.business.agregadorinvestimentos.controller;

/*
    Por que usar record?
    Porque ela:
    Cria automaticamente:

    Construtor, Getters, equals(), hashCode() e toString()
    É mais limpa e enxuta
    Não permite mudanças nos campos (são "final" por padrão → imutabilidade)

    Ideal pra DTOs
    DTOs são só "transportadores de dados", não têm lógica nem precisam ser alterados.
    Os dados vêm do front e vão direto pro backend ou vice-versa.
    Uma vez criado, o DTO não precisa ser modificado → por isso, imutabilidade (característica do record) faz todo sentido !
*/

import java.time.Instant;
import java.util.UUID;

// Responde para Saída
public record UserResponseDTO(UUID id, String userName, String email, Instant createdAt, Instant updatedAt) {
}

//@RequestBody → deve ser o DTO de entrada (UserRequestDTO)
//ResponseEntity<User> → pode trocar por ResponseEntity<UserResponseDTO> para não retornar dados sensíveis
//ResponseDTO é para responder, não para receber