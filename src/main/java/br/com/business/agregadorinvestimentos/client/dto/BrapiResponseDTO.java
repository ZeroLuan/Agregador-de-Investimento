package br.com.business.agregadorinvestimentos.client.dto;

import java.util.List;

public record BrapiResponseDTO(
        List<StockDto> results
){}
