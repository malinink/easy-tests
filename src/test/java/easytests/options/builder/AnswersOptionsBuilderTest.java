package easytests.options.builder;

import easytests.options.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * @author rezenbekk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswersOptionsBuilderTest {
    @Autowired
    private AnswersOptionsBuilderInterface answersOptionsBuilder;

    @MockBean
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final QuestionsOptionsInterface questionsOptions = new QuestionsOptions();
        given(this.questionsOptionsBuilder.forAuth()).willReturn(questionsOptions);
        Assert.assertEquals(new AnswersOptions().withQuestion(questionsOptions), this.answersOptionsBuilder.forAuth());

    }

    @Test
    public void testForDelete() throws Exception {
        final AnswersOptionsInterface answersOptions = new AnswersOptions();
        Assert.assertEquals(new AnswersOptions(), this.answersOptionsBuilder.forDelete());
    }

}
