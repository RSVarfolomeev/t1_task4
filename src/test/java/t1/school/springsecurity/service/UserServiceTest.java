package t1.school.springsecurity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import t1.school.springsecurity.model.Role;
import t1.school.springsecurity.model.User;
import t1.school.springsecurity.model.exception.CreateUserException;
import t1.school.springsecurity.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void save() {

        User userToDB = new User();
        userToDB.setId(1L);
        userToDB.setUsername("testName");
        userToDB.setPassword("testPassword");
        userToDB.setEmail("testEmail");
        userToDB.setRole(Role.ROLE_USER);

        User userFromDBForMock = new User();
        userFromDBForMock.setId(1L);
        userFromDBForMock.setUsername("testName");
        userFromDBForMock.setPassword("testPassword");
        userFromDBForMock.setEmail("testEmail");
        userFromDBForMock.setRole(Role.ROLE_USER);

        Mockito.doReturn(userFromDBForMock)
                .when(userRepository)
                .save(Mockito.any(User.class));

        User userForCheck = userService.save(userToDB);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any(User.class));


        Assertions.assertNotNull(userForCheck);
        Assertions.assertEquals(userToDB.getUsername(), userForCheck.getUsername());
        Assertions.assertEquals(userToDB.getPassword(), userForCheck.getPassword());
        Assertions.assertEquals(userToDB.getEmail(), userForCheck.getEmail());
        Assertions.assertEquals(userToDB.getRole(), userForCheck.getRole());
    }

    @Test
    @DisplayName("Проверка выброса исключения при наличии юзера в БД с таким же именем")
    void createWithSuchName() {
        User userToDB = new User();
        userToDB.setId(1L);
        userToDB.setUsername("testName");
        userToDB.setPassword("testPassword");
        userToDB.setEmail("testEmail");
        userToDB.setRole(Role.ROLE_USER);

        Mockito.doReturn(true)
                .when(userRepository)
                .existsByUsername(Mockito.any(String.class));

        assertThrows(CreateUserException.class, () -> userService.create(userToDB));
        verify(userRepository, times(1)).existsByUsername(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Проверка выброса исключения при наличии юзера в БД с таким же email")
    void createWithSuchEmail() {
        User userToDB = new User();
        userToDB.setId(1L);
        userToDB.setUsername("testName");
        userToDB.setPassword("testPassword");
        userToDB.setEmail("testEmail");
        userToDB.setRole(Role.ROLE_USER);

        Mockito.doReturn(true)
                .when(userRepository)
                .existsByEmail(Mockito.any(String.class));

        assertThrows(CreateUserException.class, () -> userService.create(userToDB));
        verify(userRepository, times(1)).existsByEmail(Mockito.any(String.class));
    }

    @Test
    void getByUsername() {
        User userFromDBForMock = new User();
        userFromDBForMock.setId(1L);
        userFromDBForMock.setUsername("testName");
        userFromDBForMock.setPassword("testPassword");
        userFromDBForMock.setEmail("testEmail");
        userFromDBForMock.setRole(Role.ROLE_USER);

        String username = "username";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userFromDBForMock));

        User userForCheck = userService.getByUsername(username);

        assertEquals(userFromDBForMock, userForCheck);
        verify(userRepository, times(1)).findByUsername(anyString());
    }
}