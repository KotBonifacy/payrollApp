package qa.test.payrollApp.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    @SuppressWarnings("unused")
    public ResourceNotFoundException() {
        super();
    }

    @SuppressWarnings("unused")
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @SuppressWarnings("unused")
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
