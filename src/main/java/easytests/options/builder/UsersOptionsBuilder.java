package easytests.options.builder;

import easytests.options.UsersOptions;
import easytests.options.UsersOptionsInterface;
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
