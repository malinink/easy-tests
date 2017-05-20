package easytests.options.builder;

import easytests.options.SubjectsOptions;
import easytests.options.SubjectsOptionsInterface;
import easytests.options.UsersOptions;
import easytests.options.UsersOptionsInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersOptionsBuilderTest {
    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @MockBean
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final UsersOptionsInterface usersOptions = this.usersOptionsBuilder.forAuth();

        Assert.assertEquals(new UsersOptions(), usersOptions);
    }

    @Test
    public void testForDelete() throws Exception {
        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();
        given(this.subjectsOptionsBuilder.forDelete()).willReturn(subjectsOptions);

        final UsersOptionsInterface usersOptions = this.usersOptionsBuilder.forDelete();

        Assert.assertEquals(new UsersOptions().withSubjects(subjectsOptions), usersOptions);
    }
}
