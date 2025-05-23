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

import java.time.Instant;
import java.util.UUID;

// Responde para Saída
public record UserResponseDTO(
        UUID id,
        String userName,
        String email,
        Instant createdAt,
        Instant updatedAt
) {}

// DTO de resposta (saída) utilizado para retornar dados do usuário sem expor informações sensíveis, como a senha.
// @RequestBody deve utilizar o DTO de entrada (UserRequestDTO).
// ResponseEntity<User> deve ser evitado em respostas — prefira ResponseEntity<UserResponseDTO> para proteger os dados.
// UserResponseDTO é usado apenas para **responder** requisições, não para receber dados.