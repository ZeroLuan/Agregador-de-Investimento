package br.com.business.agregadorinvestimentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_billingaddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddress {

    @Id
    @Column(name = "account_id") // Mesmo nome do atributo account
    private UUID id;

    @OneToOne
    @MapsId // Garante que via pegar o identificador e colocar no atributo id logo a cima
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;


}
