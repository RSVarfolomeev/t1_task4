package t1.school.springsecurity.model.exception;

public class CreateUserException extends RuntimeException {

    public CreateUserException() {
    }

    public CreateUserException(String message) {
        super(message);
    }
}
