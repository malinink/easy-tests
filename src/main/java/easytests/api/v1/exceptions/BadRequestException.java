package easytests.api.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author malinink
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String reason) {
        super(reason);
    }
}
