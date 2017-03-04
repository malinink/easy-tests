package easytests.services;

import easytests.entities.*;
import easytests.mappers.QuizzesMapper;
import org.junit.*;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import java.util.List;

/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizzesServiceTest {

    @Autowired
    private QuizzesService quizzesService;

    @MockBean
    private QuizzesMapper quizzesMapper;

    @Test
    public void findTest() {

        this.quizzesService.find(1);
        verify(this.quizzesMapper, times(1)).find(1);

    }

    @Test
    public void findAllTest() {

        this.quizzesService.findAll();
        verify(this.quizzesMapper, times(1)).findAll();

    }

    @Test
    public void findByIssueTest() {

        IssueInterface issue = Mockito.mock(IssueInterface.class);
        Mockito.when(issue.getId()).thenReturn(1);
        List<QuizInterface> quizzes = this.quizzesService.findByIssue(issue);

        verify(this.quizzesMapper, times(1)).findByIssue(issue);
    }

    @Test
    public void saveCreatesEntityTest() {

        final QuizInterface quiz = new Quiz();
        quiz.setIssueId(1);

        this.quizzesService.save(quiz);

        verify(this.quizzesMapper, times(1)).insert(quiz);

    }

    @Test
    public void saveUpdatesEntityTest() {

        final QuizInterface quiz = new Quiz();
        quiz.setId(1);

        this.quizzesService.save(quiz);

        verify(this.quizzesMapper, times(1)).update(quiz);

    }

}
