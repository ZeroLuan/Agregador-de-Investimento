package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.dtos.StockRequestDTO;
import br.com.business.agregadorinvestimentos.model.Stock;
import br.com.business.agregadorinvestimentos.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class StockController {

    private final StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/createStock")
    public ResponseEntity<Void> createStock(@RequestBody StockRequestDTO stockRequestDTO){

        stockService.createStock(stockRequestDTO);

        return ResponseEntity.ok().build();
    }


}
