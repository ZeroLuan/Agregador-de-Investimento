package br.com.business.agregadorinvestimentos.mapper;

import br.com.business.agregadorinvestimentos.dtos.StockRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.UserRequestDTO;
import br.com.business.agregadorinvestimentos.model.Stock;
import br.com.business.agregadorinvestimentos.model.User;

public class StockMapper {

    public static Stock toEntity(StockRequestDTO dto) {
        return new Stock(
                dto.stockId(),
                dto.description()
        );
    }


}
