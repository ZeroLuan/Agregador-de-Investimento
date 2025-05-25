package br.com.business.agregadorinvestimentos.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Representa a entidade User mapeada para a tabela "tb_users" no banco de dados.
 * Contém informações básicas como nome de usuário, email, senha e timestamps de criação e atualização.
 **/

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Identificador único do usuário, gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    /**
     * Nome de usuário. Deve ser único.
     */
    @Column(name = "user_name", unique = true)
    private String userName;

    /**
     * Email do usuário. Deve ser único.
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Senha do usuário.
     */
    @Column(name = "password")
    private String password;

    /**
     * Timestamp de criação automática do usuário.
     */
    @CreationTimestamp
    private Instant creationTimestamp;

    /**
     * Timestamp de atualização automática do usuário.
     */
    @UpdateTimestamp
    private Instant updateTimestamp;


    @OneToMany(mappedBy = "user")
    private List<Account> accounts;



    /**
     * Construtor personalizado para criação de usuário com nome, email e senha.
     *
     * @param userName nome de usuário
     * @param email email do usuário
     * @param password senha do usuário
     */
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}
