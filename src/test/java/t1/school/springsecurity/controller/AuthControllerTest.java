package t1.school.springsecurity.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import t1.school.springsecurity.model.dto.JwtAuthenticationResponse;
import t1.school.springsecurity.model.dto.LoginRequest;
import t1.school.springsecurity.model.dto.RegisterRequest;
import t1.school.springsecurity.service.AuthenticationService;

import static org.mockito.Mockito.*;;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {


    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    void register() {
        RegisterRequest registerRequest = new RegisterRequest("testName", "testEmail", "testPassword");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("testToken");

        //Что нам вернет сервис
        when(authenticationService.register(registerRequest)).thenReturn(jwtAuthenticationResponse);

        JwtAuthenticationResponse jwtAuthenticationResponseForCheck = authController.register(registerRequest);

        Assertions.assertNotNull(jwtAuthenticationResponseForCheck);
        verify(authenticationService, times(1)).register(any(RegisterRequest.class));
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest("testEmail", "testPassword");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("testToken");

        //Что нам вернет сервис
        when(authenticationService.login(loginRequest)).thenReturn(jwtAuthenticationResponse);

        JwtAuthenticationResponse jwtAuthenticationResponseForCheck = authController.login(loginRequest);

        Assertions.assertNotNull(jwtAuthenticationResponseForCheck);
        verify(authenticationService, times(1)).login(any(LoginRequest.class));
    }
}