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

    @MockBean
    private SubjectsMapper subjectsMapper;

    @MockBean
    private TopicsMapper topicsMapper;

    @MockBean
    private IssueStandardService issueStandardService;

    @Autowired
    private SubjectsService subjectsService;

    @Test
    public void find() {
        this.subjectsService.find(1);
        verify(this.subjectsService, times(1)).find(1);
    }

    @Test
    public void saveCreatesEntityTest() {

        final Subject subject = new Subject();
        subject.setName("test");

        this.subjectsService.save(subject);

        verify(this.subjectsMapper, times(1)).insert(subject);
        verify(this.issueStandardService, times(1)).save(subject.issueStandard);

    }

    @Test
    public void saveUpdatesEntityTest() {

        final Subject subject = new Subject();

        subject.setId(1);
        subject.setName("updated");
        subject.getIssueStandard().setId(2);

        this.subjectsService.save(subject);

        verify(this.subjectsMapper, times(1)).update(subject);
        verify(this.issueStandardService, times(1)).save(subject.issueStandard);

    }

    @Test
    public void saveWithTopicsEntityTest() {

        final Subject subject = subjectsMapper.find(1);

        subject.setName("updated");

        Topic topic = new Topic();

        subject.getTopics().get(1).setName("test");

        this.subjectsService.save(subject);

        verify(this.subjectsMapper, times(2)).update(subject);
        verify(this.topicsMapper, times(1)).insert(subject);
        verify(this.issueStandardService, times(1)).save(subject.issueStandard);

    }

}
