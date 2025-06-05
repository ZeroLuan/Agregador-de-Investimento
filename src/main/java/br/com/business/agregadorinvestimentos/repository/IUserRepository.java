package br.com.business.agregadorinvestimentos.repository;

import br.com.business.agregadorinvestimentos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Interface responsável pelo acesso aos dados da entidade User.
 * Estende JpaRepository para fornecer operações padrão de CRUD e suporte a paginação e ordenação.
 * A anotação @Repository permite que o Spring reconheça e gerencie essa interface como um componente de repositório.
 **/

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {


}
