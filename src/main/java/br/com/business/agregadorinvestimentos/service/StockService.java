package br.com.business.agregadorinvestimentos.service;

import br.com.business.agregadorinvestimentos.dtos.StockRequestDTO;
import br.com.business.agregadorinvestimentos.mapper.StockMapper;
import br.com.business.agregadorinvestimentos.model.Stock;
import br.com.business.agregadorinvestimentos.repository.IStockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {


    private final IStockRepository stockRepository;

    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }



    public void createStock(StockRequestDTO stockRequestDTO) {

        Stock stock = StockMapper.toEntity(stockRequestDTO);

        stockRepository.save(stock);

    }



}
