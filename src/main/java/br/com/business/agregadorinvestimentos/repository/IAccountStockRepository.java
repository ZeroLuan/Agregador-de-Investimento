package br.com.business.agregadorinvestimentos.repository;

import br.com.business.agregadorinvestimentos.model.AccountStock;
import br.com.business.agregadorinvestimentos.model.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
