package br.com.business.agregadorinvestimentos.mapper;

import br.com.business.agregadorinvestimentos.dtos.UserRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.UserResponseDTO;
import br.com.business.agregadorinvestimentos.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        return new User(
                dto.userName(),
                dto.email(),
                dto.password()
        );
    }

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreationTimestamp(),
                user.getUpdateTimestamp(),
                user.getAccounts()
        );
    }

}
