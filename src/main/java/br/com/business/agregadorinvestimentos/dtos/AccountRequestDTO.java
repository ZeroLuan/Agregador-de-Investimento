package br.com.business.agregadorinvestimentos.dtos;

public record AccountRequestDTO(
        String description,
        String street,
        Integer number
) {}
