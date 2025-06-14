package br.com.business.agregadorinvestimentos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn // Passa a Chave primaria de account para BillingAddress
    private BillingAddress billingAddress;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account")     // Verificar o relacionamento !!!
    private List<AccountStock> accountStocks;


    // A necessidade desse construtor é por contra do AccountMaper, que não consegue instanciar um Account sem um BillingAddress.
    public Account(String description, String street, Integer number) {
        this.description = description;
        this.billingAddress = new BillingAddress(accountId, street, number, this);
    }
}
