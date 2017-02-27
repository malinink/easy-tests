package easytests.services;

import easytests.entities.IssueStandardInterface;
import easytests.entities.Subject;
import easytests.entities.User;
import easytests.entities.UserInterface;
import easytests.mappers.SubjectsMapper;
import org.junit.*;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectsServiceTest {

    @Autowired
    private SubjectsService subjectsService;

    @MockBean
    private SubjectsMapper subjectsMapper;

    @Test
    public void findTest() {
        this.subjectsService.find(1);
        verify(this.subjectsMapper, times(1)).find(1);
    }

    @Test
    public void findAllTest() {
        this.subjectsService.findAll();
        verify(this.subjectsMapper, times(1)).findAll();
    }

    @Test
    public void findByUserTest() {

        final UserInterface user = new User();

        user.setId(3);
        this.subjectsService.findByUser(user);

        verify(this.subjectsMapper, times(1)).findByUser(user);
    }

    @Test
    public void saveCreatesEntityTest() {

        final Subject subject = new Subject();
        subject.setName("test");

        this.subjectsService.save(subject);

        verify(this.subjectsMapper, times(1)).insert(subject);

    }

    @Test
    public void saveUpdatesEntityTest() {

        final Subject subject = new Subject();

        subject.setId(1);
        subject.setName("updated");

        this.subjectsService.save(subject);

        verify(this.subjectsMapper, times(1)).update(subject);

    }

}
