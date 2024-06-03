package t1.school.springsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.school.springsecurity.model.dto.JwtAuthenticationResponse;
import t1.school.springsecurity.model.dto.LoginRequest;
import t1.school.springsecurity.model.dto.RegisterRequest;
import t1.school.springsecurity.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.register(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody @Valid LoginRequest request) {
        return authenticationService.login(request);
    }
}
