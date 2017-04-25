package easytests.common.validators;

import easytests.common.dto.ModelDtoInterface;
import org.springframework.validation.Errors;


/**
 * @author malinink
 */
public abstract class AbstractDtoValidator extends AbstractValidator {
    protected void validateIdEquals(Errors errors, ModelDtoInterface modelDto) {
        final Integer routeId = modelDto.getRouteId();
        final Integer id = modelDto.getId();
        if (((routeId == null) && (id != null)) || ((routeId != null) && !routeId.equals(id))) {
            reject(errors, "id", "Id must be the same");
        }
    }
}
