package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.Question;
import easytests.entities.QuestionInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class QuestionsMapperTest {

    @Autowired
    private QuestionsMapper questionsMapper;

    @Test
    public void testFind() throws Exception {
        final QuestionInterface question = this.questionsMapper.find(1);
        Assert.assertEquals((long) 1, (long) question.getId());
        Assert.assertEquals("test1", question.getText());
        Assert.assertEquals((long) 1, (long) question.getType());
        Assert.assertEquals((long) 1, (long) question.getTopicId());
    }

    @Test
    public void testDelete() throws Exception {
        Question question = this.questionsMapper.find(1);
        Assert.assertNotNull(question);
        this.questionsMapper.delete(question);
        question = this.questionsMapper.find(1);
        Assert.assertNull(question);
    }
}
