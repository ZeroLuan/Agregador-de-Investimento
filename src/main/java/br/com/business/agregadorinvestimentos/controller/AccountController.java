package br.com.business.agregadorinvestimentos.controller;

import br.com.business.agregadorinvestimentos.dtos.AccountRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.AccountStockResponseDTO;
import br.com.business.agregadorinvestimentos.dtos.AssociateAccountStockDTO;
import br.com.business.agregadorinvestimentos.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                               @RequestBody AssociateAccountStockDTO associateAccountStockDTO){

        log.info("✔ Recebida requisição para criar uma associação usando id da account e DTO de account.");

        accountService.associateStock(accountId, associateAccountStockDTO);

        log.info("✔ Associação Criada.");

        return ResponseEntity.ok().build();
    }


    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> getAllAccountsStock(@PathVariable("accountId") String accountId){

        log.info("✔ Recebida requisição para buscar todas associações usando id da account.");

        var stocks= accountService.listStocks(accountId);

        log.info("✔ Associações Encontradas.");

        return ResponseEntity.ok(stocks);
    }

}
