package easytests.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author malinink
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "404 Not Found")
public class NotFoundException extends RuntimeException {

}
