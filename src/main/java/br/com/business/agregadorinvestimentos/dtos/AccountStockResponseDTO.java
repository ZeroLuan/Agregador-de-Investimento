package br.com.business.agregadorinvestimentos.dtos;

public record AccountStockResponseDTO(
        String stockId,
        Integer quantity,
        Double total
){}
