package br.com.business.agregadorinvestimentos.service;

import br.com.business.agregadorinvestimentos.client.IBrapiClient;
import br.com.business.agregadorinvestimentos.dtos.AccountStockResponseDTO;
import br.com.business.agregadorinvestimentos.dtos.AssociateAccountStockDTO;
import br.com.business.agregadorinvestimentos.model.Account;
import br.com.business.agregadorinvestimentos.model.AccountStock;
import br.com.business.agregadorinvestimentos.model.AccountStockId;
import br.com.business.agregadorinvestimentos.model.Stock;
import br.com.business.agregadorinvestimentos.repository.IAccountRepository;
import br.com.business.agregadorinvestimentos.repository.IAccountStockRepository;
import br.com.business.agregadorinvestimentos.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private final IAccountRepository accountRepository;

    private final IStockRepository stockRepository;

    private final IAccountStockRepository accountStockRepository;

    private final IBrapiClient brapiClient;

    public AccountService(IAccountRepository accountRepository, IStockRepository stockRepository, IAccountStockRepository accountStockRepository, IBrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }


    public void associateStock(String accountId, AssociateAccountStockDTO associateAccountStockDTO) {

        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                        "Account não encontrada com o ID: " + accountId)
                );


        Stock stock = stockRepository.findById(associateAccountStockDTO.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                        "Stock não encontrado com o ID: " + associateAccountStockDTO.stockId())
                );


        AccountStockId id = new AccountStockId(account.getAccountId(), stock.getStockId());
        AccountStock entity = new AccountStock(
                id,
                account,
                stock,
                associateAccountStockDTO.quantity()
        );


        accountStockRepository.save(entity);


    }


    public List<AccountStockResponseDTO> listStocks(String accountId) {

        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,
                        "Account não encontrada com o ID: " + accountId)
                );

        // Peguei todos os elementos da minha lista associativa e estou fazendo um novo DTO passando os parâmetros específicos
        return account.getAccountStocks()
                .stream()
                .map(as ->
                        new AccountStockResponseDTO(
                                as.getStock().getStockId(),
                                as.getQuantity(),
                                getTotal(as.getQuantity(), as.getStock().getStockId())
                        )
                ).toList();

    }

    private Double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }
}
