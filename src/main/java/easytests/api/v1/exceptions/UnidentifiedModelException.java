package easytests.api.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author malinink
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "id must be present")
public class UnidentifiedModelException extends BadRequestException {
}
