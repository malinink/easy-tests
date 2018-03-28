package easytests.swagger;

import com.atlassian.oai.validator.report.ValidationReport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author atlassian
 * In case the request is invalid.
 * <p>
 * The requests response will be mapped to an appropriate {@link HttpStatus}.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    private static final String MESSAGE_DELIMITER = ", ";

    protected final ValidationReport validationReport;

    protected final String message;

    public InvalidRequestException(final ValidationReport validationReport) {
        this.validationReport = validationReport;
        final StringBuilder sb = new StringBuilder();
        for (final ValidationReport.Message message : this.validationReport.getMessages()) {
            sb.append(MESSAGE_DELIMITER).append(message.getMessage());
        }
        this.message = sb.substring(Math.min(sb.length(), MESSAGE_DELIMITER.length()));
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public ValidationReport getValidationReport() {
        return this.validationReport;
    }
}
