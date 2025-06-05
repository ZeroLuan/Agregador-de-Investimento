package br.com.business.agregadorinvestimentos.service;

import br.com.business.agregadorinvestimentos.dtos.AccountRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.AccountResponseDTO;
import br.com.business.agregadorinvestimentos.dtos.UserRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.UserResponseDTO;
import br.com.business.agregadorinvestimentos.mapper.AccountMapper;
import br.com.business.agregadorinvestimentos.mapper.UserMapper;
import br.com.business.agregadorinvestimentos.model.Account;
import br.com.business.agregadorinvestimentos.model.BillingAddress;
import br.com.business.agregadorinvestimentos.model.User;
import br.com.business.agregadorinvestimentos.repository.IAccountRepository;
import br.com.business.agregadorinvestimentos.repository.IBillingAddressRepository;
import br.com.business.agregadorinvestimentos.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    private final IUserRepository userRepository;


    private final IAccountRepository accountRepository;


    private final IBillingAddressRepository billingAddressRepository;

    public UserService(IUserRepository userRepository, IAccountRepository accountRepository, IBillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }



    public UUID createUser(UserRequestDTO userRequestDTO){

        User entity = UserMapper.toEntity(userRequestDTO);

        User userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public UserResponseDTO getUserById(UUID userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + userId));

        return UserMapper.toResponse(user);
    }

    public List<UserResponseDTO> getAllUsers(){

        List<User> userList = userRepository.findAll();

        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        for (User user : userList) {
            userResponseDTOs.add(UserMapper.toResponse(user));
        }

        return userResponseDTOs;
    }

    public void updateUserById(UUID userId, UserRequestDTO userRequestDTO){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + userId));

        user.setUserName(userRequestDTO.userName());
        user.setEmail(userRequestDTO.email());
        user.setPassword(userRequestDTO.password());

        userRepository.save(user);

    }

    public void deleteUserById(UUID userId){

        boolean existsUser = userRepository.existsById(userId);

        if(existsUser){
            userRepository.deleteById(userId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + userId);
        }

    }


    public void createAccount(String userId, AccountRequestDTO accountRequestDTO) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com o ID: " + userId));

        // Cria uma nova Account (sem billingAddress ainda)
        Account account = new Account(
                null,
                user,
                null,
                accountRequestDTO.description(),
                new ArrayList<>()
        );

        // Cria o BillingAddress (sem salvar ainda)
        BillingAddress billingAddress = new BillingAddress();

        billingAddress.setStreet(accountRequestDTO.street());
        billingAddress.setNumber(accountRequestDTO.number());
        billingAddress.setAccount(account); // associa Account à BillingAddress

        account.setBillingAddress(billingAddress); // associa BillingAddress à Account

        // Salva apenas a account, o cascade ALL cuidará de salvar o BillingAddress
        accountRepository.save(account);


    }


    public List<AccountResponseDTO> getAllAccounts(String userId) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Account> accounts = user.getAccounts();

        List<AccountResponseDTO> accountResponseDTOs = new ArrayList<>();

        for(Account account : accounts){
            accountResponseDTOs.add(AccountMapper.toResponse(account));
        }

        return accountResponseDTOs;
    }
}
