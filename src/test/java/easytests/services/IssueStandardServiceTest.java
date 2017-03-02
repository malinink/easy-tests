package easytests.services;

import easytests.entities.*;
import easytests.mappers.IssueStandardMapper;
import easytests.mappers.IssueStandardQuestionTypeOptionMapper;
import easytests.mappers.IssueStandardTopicPriorityMapper;
import org.junit.*;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardServiceTest {

    @Mock
    private IssueStandardMapper issueStandardMapper;

    @Mock
    private IssueStandardTopicPriorityMapper topicPriorityMapper;

    @Mock
    private IssueStandardQuestionTypeOptionMapper questionTypeOptionMapper;

    @InjectMocks
    private IssueStandardService issueStandardService;

    @Test
    public void findAllTest() throws Exception {
        this.issueStandardService.findAll();
        verify(this.issueStandardMapper, times(1)).findAll();
    }

    @Test
    public void findTest() throws Exception {
        this.issueStandardService.find(1);
        verify(this.issueStandardMapper, times(1)).find(1);
    }

    @Test
    public void findBySubjectTest() throws Exception {
        SubjectInterface subject = Mockito.mock(SubjectInterface.class);
        Mockito.when(subject.getId()).thenReturn(1);

        this.issueStandardService.findBySubject(subject);
        verify(this.issueStandardMapper, times(1)).findBySubject(subject);
    }

    @Test
    public void saveCreatesEntitiesTest() throws Exception {
        IssueStandard issueStandard = new IssueStandard();

        List<IssueStandardTopicPriorityInterface> topicPriorities = new ArrayList<>(1);
        topicPriorities.add(new IssueStandardTopicPriority());

        List<IssueStandardQuestionTypeOptionInterface> questionTypeOptions = new ArrayList<>(1);
        questionTypeOptions.add(new IssueStandardQuestionTypeOption());

        issueStandard.setIssueStandardTopicPriorities(topicPriorities);
        issueStandard.setIssueStandardQuestionTypeOptions(questionTypeOptions);

        this.issueStandardService.save(issueStandard);
        verify(this.issueStandardMapper, times(1)).insert(issueStandard);
        verify(this.topicPriorityMapper, times(1)).insert(topicPriorities.get(0));
        verify(this.questionTypeOptionMapper, times(1)).insert(questionTypeOptions.get(0));
    }

    @Test
    public void saveUpdatesEntitiesTest() throws Exception {
        IssueStandard issueStandard = new IssueStandard();
        issueStandard.setId(1);

        List<IssueStandardTopicPriorityInterface> topicPriorities = new ArrayList<>(1);
        topicPriorities.add(new IssueStandardTopicPriority()
                .setId(10)
                .setTopicId(20)
                .setPriority(Priority.HIGH)
                .setIssueStandardId(1));

        List<IssueStandardQuestionTypeOptionInterface> questionTypeOptions = new ArrayList<>(1);
        questionTypeOptions.add(new IssueStandardQuestionTypeOption()
                .setId(11)
                .setQuestionTypeId(21)
                .setIssueStandardId(1));

        issueStandard.setIssueStandardTopicPriorities(topicPriorities);
        issueStandard.setIssueStandardQuestionTypeOptions(questionTypeOptions);

        this.issueStandardService.save(issueStandard);
        verify(this.issueStandardMapper, times(1)).update(issueStandard);
        verify(this.topicPriorityMapper, times(1)).update(topicPriorities.get(0));
        verify(this.questionTypeOptionMapper, times(1)).update(questionTypeOptions.get(0));
    }

    @Test
    public void saveUpdatesAndDeletesEntitiesTest() throws Exception {
        IssueStandard issueStandard = new IssueStandard();
        issueStandard.setId(1);

        List<IssueStandardTopicPriorityInterface> topicPriorities = new ArrayList<>(1);
        topicPriorities.add(new IssueStandardTopicPriority().setId(10));

        List<IssueStandardQuestionTypeOptionInterface> questionTypeOptions = new ArrayList<>(1);
        questionTypeOptions.add(new IssueStandardQuestionTypeOption().setId(11));

        issueStandard.setIssueStandardTopicPriorities(topicPriorities);
        issueStandard.setIssueStandardQuestionTypeOptions(questionTypeOptions);

        this.issueStandardService.save(issueStandard);
        verify(this.issueStandardMapper, times(1)).update(issueStandard);
        verify(this.topicPriorityMapper, times(1)).delete(topicPriorities.get(0));
        verify(this.questionTypeOptionMapper, times(1)).delete(questionTypeOptions.get(0));
    }

    @Test
    public void deleteTest() throws Exception {
        IssueStandard issueStandard = new IssueStandard();
        issueStandard.setId(1);
        this.issueStandardService.delete(issueStandard);
        verify(this.issueStandardMapper, times(1)).delete(issueStandard);
    }
}
