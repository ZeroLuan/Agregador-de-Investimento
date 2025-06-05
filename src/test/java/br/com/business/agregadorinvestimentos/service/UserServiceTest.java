package br.com.business.agregadorinvestimentos.service;

import br.com.business.agregadorinvestimentos.dtos.UserRequestDTO;
import br.com.business.agregadorinvestimentos.dtos.UserResponseDTO;
import br.com.business.agregadorinvestimentos.model.Account;
import br.com.business.agregadorinvestimentos.model.User;
import br.com.business.agregadorinvestimentos.repository.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class) // Integra o Spring no JUnit 5
class UserServiceTest {

    // Arrange - (Preparação) Arrumar/Organizer o teste
    // Act - (Ação) Chamar o trecho do teste
    // Assert - (Afirmação) Verificar se ele chamou tudo como deveria

    @Mock // Para não depender de dependências exteriores, um objeto falso da classe informada
    private IUserRepository userRepository;

    @InjectMocks // Cria o objeto real e injeta os mocks, Para injetar o Mock dentro da service, e criar a instancia dela.
    private UserService userService;

    @Captor // 	Permite capturar argumentos de métodos mockados
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    // No JUnit 5 tem essa funcionalidade, "@Nested" Class
    // Basicamente a gente consegue definir uma sub classe dentro aqui do nosso teste
    // para poder organizar melhor os nossos testes


    List<Account> accounts = null;


    @Nested // Permite criar uma classe interna dentro da classe de testes
    class CreateUser{

        @Test
        @DisplayName("Should create a user with success")// Para ficar mais bonito nos Logs forma de documentar, convenção
        void shouldCreateAUserWithSuccess(){

            // Arrange - Arrumar/Organizer o teste
            User user = new User(
                    UUID.randomUUID(),
                    "userName",
                    "email@email.com",
                    "123",
                    Instant.now(),
                    null,
                    accounts
            );

            //o retorno deve ser um (user).quando(userRepository).save(Captura o argumento do método save)
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture()); // userArgumentCaptor.capture() = Você está dizendo que quando save(...) for chamado, ele deve capturar o argumento que for passado; E devolver o objeto user simulado.

            UserRequestDTO input = new UserRequestDTO(
                    "userName",
                    "email@email.com",
                    "123"
            );



            // Act - Chamar o trecho do teste
            UUID output = userService.createUser(input);

            // Assert - Verificar se ele chamou tudo como deveria
            assertNotNull(output);

            User userCaptorValue = userArgumentCaptor.getValue();


            // Esse método recebe 2 parâmetros, no primeiro paramento indica o valor esperado, já no outro récebe o atributo ou método que guarda a resposta do método de teste
            assertEquals(input.userName(), userCaptorValue.getUserName());
            assertEquals(input.email(), userCaptorValue.getEmail());
            assertEquals(input.password(), userCaptorValue.getPassword());

        }


        @Test
        @DisplayName("Should throw exception when error occurred")
        void shouldThrowExceptionWhenErrorOccurred(){

            // Arrange - Arrumar/Organizer o teste
            doThrow(new RuntimeException()).when(userRepository).save(any());

            UserRequestDTO input = new UserRequestDTO(
                    "userName",
                    "email@email.com",
                    "123"
            );

            // Act - & Assert Chamar o trecho do teste
            assertThrows(RuntimeException.class, () -> userService.createUser(input));

        }

    }


    @Nested
    class getUserById{

        @Test
        @DisplayName("Should get user by id with success when optional is present")
        void shouldGetUserByIdWithSuccessWhenOptionalIsPresent(){


            // Arrange - Arrumar/Organizer o teste
            User user = new User(
                    UUID.randomUUID(),
                    "userName",
                    "email@email.com",
                    "123",
                    Instant.now(),
                    null,
                    accounts
            );

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());


            // Act

            UserResponseDTO output = userService.getUserById(user.getUserId());

            // Assert

            assertNotNull(output);
            assertEquals(user.getUserId(), output.id());
            assertEquals(user.getUserName(), output.userName());
            assertEquals(user.getEmail(), output.email());

        }


        @Test
        @DisplayName("Should get user by id with success when optional is empty")
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty(){


            // Arrange - Arrumar/Organizer o teste
            UUID userId = UUID.randomUUID();
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(uuidArgumentCaptor.capture());

            // Act + Assert
            ResponseStatusException exception = assertThrows(
                    ResponseStatusException.class,
                    () -> userService.getUserById(userId)
            );

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            assertTrue(exception.getReason().contains("Usuário não encontrado"));
            assertEquals(userId, uuidArgumentCaptor.getValue());


        }

    }

}