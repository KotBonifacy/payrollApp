package qa.test.payrollApp.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RecordAlreadyExistException extends RuntimeException{
    @SuppressWarnings("unused")
    public RecordAlreadyExistException() {
        super();
    }

    @SuppressWarnings("unused")
    public RecordAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    @SuppressWarnings("unused")
    public RecordAlreadyExistException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public RecordAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
