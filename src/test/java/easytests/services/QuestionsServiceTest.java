package easytests.services;

import easytests.entities.Question;
import easytests.mappers.QuestionsMapper;
import org.junit.*;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

/**
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsServiceTest {

    @MockBean
    private QuestionsMapper questionsMapper;

    @Autowired
    private QuestionsService questionsService;

    @Test
    public void saveCreatesEntityTest() {
        final Question question = new Question();
        this.questionsService.save(question);
        verify(this.questionsMapper, times(1)).insert(question);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final Question question = new Question();
        question.setId(1);
        this.questionsService.save(question);
        verify(this.questionsMapper, times(1)).update(question);
    }
}
