package br.com.business.agregadorinvestimentos.dtos;

import java.util.UUID;

public record AccountResponseDTO (
        UUID accountId,
        String description
){}
