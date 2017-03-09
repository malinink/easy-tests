package easytests.services;

import easytests.entities.Answer;
import easytests.mappers.AnswersMapper;
import org.junit.*;
import org.junit.runner.*;

import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswersServiceTest {

    @MockBean
    private AnswersMapper answersMapper;

    @Autowired
    private AnswersService answersService;

    @Test
    public void saveCreatesEntityTest() {
        final Answer answer = new Answer();
        this.answersService.save(answer);
        verify(this.answersMapper, times(1)).insert(answer);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final Answer answer = new Answer();
        answer.setId(1);
        this.answersService.save(answer);
        verify(this.answersMapper, times(1)).update(answer);
    }
}
