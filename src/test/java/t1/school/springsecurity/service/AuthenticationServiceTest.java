package t1.school.springsecurity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import t1.school.springsecurity.model.User;
import t1.school.springsecurity.model.dto.JwtAuthenticationResponse;
import t1.school.springsecurity.model.dto.RegisterRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthenticationService.class)
class AuthenticationServiceTest {

    @MockBean
    private UserService userService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void register() {
        RegisterRequest registerRequest = new RegisterRequest("testName", "testEmail", "testPassword");

        when(userService.create(any(User.class))).thenReturn(new User());
        when(jwtService.generateToken(any(User.class))).thenReturn(String.valueOf(new JwtAuthenticationResponse("testToken")));

        var jwt = authenticationService.register(registerRequest);

        assertNotNull(jwt);
    }
}