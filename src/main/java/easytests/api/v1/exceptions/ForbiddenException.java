package easytests.api.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author vkpankov
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "403 Access denied")
public class ForbiddenException extends RuntimeException {

}
