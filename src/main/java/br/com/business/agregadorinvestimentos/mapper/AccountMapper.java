package br.com.business.agregadorinvestimentos.mapper;

import br.com.business.agregadorinvestimentos.dtos.AccountRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.AccountResponseDTO;
import br.com.business.agregadorinvestimentos.model.Account;

public class AccountMapper {

    public static Account toEntity(AccountRequestDTO dto) {
        return new Account(
                dto.description(),
                dto.street(),
                dto.number()
        );
    }

    public static AccountResponseDTO toResponse(Account dto) {
        return new AccountResponseDTO(
                dto.getAccountId(),
                dto.getDescription()
        );
    }


}
