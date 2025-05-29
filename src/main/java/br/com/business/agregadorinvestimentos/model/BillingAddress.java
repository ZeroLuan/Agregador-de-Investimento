package br.com.business.agregadorinvestimentos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "account_id") // Mesmo id do atributo account
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId // Garante que via pegar o identificador e colocar no atributo id logo a cima
    @JsonIgnore // Adicione esta anotação para evitar a recursão infinita
    @JoinColumn(name = "account_id")
    private Account account;


}
