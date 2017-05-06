package easytests.options.builder;

import easytests.options.SubjectsOptions;
import easytests.options.SubjectsOptionsInterface;
import org.springframework.stereotype.Service;

/**
 * @author malinink
 */
@Service
public class SubjectsOptionsBuilder implements SubjectsOptionsBuilderInterface {
    @Override
    public SubjectsOptionsInterface forDelete() {
        // TODO vkpankov
        return new SubjectsOptions();
    }

    @Override
    public SubjectsOptionsInterface forAuth() {
        // TODO vkpankov
        return new SubjectsOptions();
    }
}
