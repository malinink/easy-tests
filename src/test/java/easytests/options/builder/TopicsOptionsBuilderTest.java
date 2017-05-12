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
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsOptionsBuilderTest {
    @Autowired
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @MockBean
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @MockBean
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();
        given(this.subjectsOptionsBuilder.forDelete()).willReturn(subjectsOptions);
        Assert.assertEquals(new TopicsOptions().withSubject(subjectsOptions), this.topicsOptionsBuilder.forAuth());
    }

    @Test
    public void testForDelete() throws Exception {
        final QuestionsOptionsInterface questionsOptions = new QuestionsOptions();
        given(this.questionsOptionsBuilder.forDelete()).willReturn(questionsOptions);
        Assert.assertEquals(new TopicsOptions().withQuestions(questionsOptions), this.topicsOptionsBuilder.forDelete());
    }
}
