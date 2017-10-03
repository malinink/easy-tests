package easytests.core.options.builder;

import easytests.core.options.UsersOptions;
import easytests.core.options.UsersOptionsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author malinink
 */
@Service
public class UsersOptionsBuilder implements UsersOptionsBuilderInterface {
    @Autowired
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Override
    public UsersOptionsInterface forDelete() {
        return new UsersOptions().withSubjects(this.subjectsOptionsBuilder.forDelete());
    }

    @Override
    public UsersOptionsInterface forAuth() {
        return new UsersOptions();
    }
}
