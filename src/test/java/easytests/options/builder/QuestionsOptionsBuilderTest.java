package easytests.options.builder;

import easytests.options.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsOptionsBuilderTest {
    @Autowired
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @MockBean
    private AnswersOptionsBuilder answersOptionsBuilder;

    @MockBean
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        given(this.topicsOptionsBuilder.forAuth()).willReturn(topicsOptions);

        final QuestionsOptionsInterface questionsOptions = this.questionsOptionsBuilder.forAuth();

        Assert.assertEquals(new QuestionsOptions().withTopic(topicsOptions), questionsOptions);
    }

    @Test
    public void testForDelete() throws Exception {
        final AnswersOptionsInterface answersOptions = new AnswersOptions();
        given(this.answersOptionsBuilder.forDelete()).willReturn(answersOptions);

        final QuestionsOptionsInterface questionsOptions = this.questionsOptionsBuilder.forDelete();

        Assert.assertEquals(new QuestionsOptions().withAnswers(answersOptions), questionsOptions);
    }
}