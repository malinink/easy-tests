package easytests.swagger;

import com.atlassian.oai.validator.report.ValidationReport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author malinink
 * In case the response is invalid.
 * <p>
 * The requests response will be mapped to an appropriate {@link HttpStatus}.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class InvalidResponseException extends InvalidRequestException {
    public InvalidResponseException(final ValidationReport validationReport) {
        super(validationReport);
    }
}
