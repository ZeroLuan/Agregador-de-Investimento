package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.dtos.StockRequestDTO;
import br.com.business.agregadorinvestimentos.model.Stock;
import br.com.business.agregadorinvestimentos.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stock")
@Slf4j
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

    @DeleteMapping("{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable String stockId){

        log.info("✔ Recebida requisição para Deletar um stock pelo ID.");

        stockService.deleteStock(stockId);

        log.info("✔ Stock removido com sucesso.");

        return ResponseEntity.noContent().build();
    }


}
