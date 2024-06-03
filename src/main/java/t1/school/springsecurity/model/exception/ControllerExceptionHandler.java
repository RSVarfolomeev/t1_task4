package t1.school.springsecurity.model.exception;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception e) {
        log.error("Перехвачено исключение: ", e);
        return "Возникла внутренняя ошибка сервера, обратитесь к администратору сервиса: " + e.getMessage();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handlerException(Exception e) {
        return "Ошибка в получаемых сервером данных: " + e.getMessage();
    }

    @ExceptionHandler(CreateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handlerException(CreateUserException e) {
        return "Ошибка при создании пользователя: " + e.getMessage();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handlerException(UsernameNotFoundException e) {
        return "Пользователь по данному логину не найден: " + e.getMessage();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handlerException(AccessDeniedException e) {
        return "Доступ запрещен: " + e.getMessage();
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handlerException(JwtException e) {
        return "Ошибка токена: " + e.getMessage();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String handlerException(BadCredentialsException e) {
        return "Неверный логин или пароль: " + e.getMessage();
    }
}