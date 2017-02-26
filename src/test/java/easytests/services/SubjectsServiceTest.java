package easytests.services;

import easytests.entities.IssueStandardInterface;
import easytests.entities.Subject;
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
    public void find() {
        this.subjectsService.find(1);
        verify(this.subjectsMapper, times(1)).find(1);
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
