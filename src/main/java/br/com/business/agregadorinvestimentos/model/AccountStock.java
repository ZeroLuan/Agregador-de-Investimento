package br.com.business.agregadorinvestimentos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_accounts_stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStock {

    // Mapeando as chaves compostas

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @MapsId("stockId")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;

}
