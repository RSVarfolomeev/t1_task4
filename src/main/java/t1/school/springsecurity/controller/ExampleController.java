package t1.school.springsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.school.springsecurity.service.UserService;

@Slf4j
@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Примеры", description = "Примеры запросов с разными правами доступа")
public class ExampleController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        log.info("Hello, authorized user!");
        return "Hello, authorized user!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        log.info("Hello, admin!");
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
    public String getAdmin() {
        log.info("Get admin");
        service.getAdmin();
        return "You are now an admin!";
    }
}