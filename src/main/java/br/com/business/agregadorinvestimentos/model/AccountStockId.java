package br.com.business.agregadorinvestimentos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable // Vai poder utilizar essa classe como um campo de identificador dentro de nossa entidade
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStockId {

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "stock_id")
    private String stockId;


}
