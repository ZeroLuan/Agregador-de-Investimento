package br.com.business.agregadorinvestimentos.repository;

import br.com.business.agregadorinvestimentos.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends JpaRepository<Stock, String> {
}
