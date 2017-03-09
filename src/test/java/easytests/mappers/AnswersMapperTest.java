package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.Answer;
import easytests.entities.AnswerInterface;
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
 * @author rezenbekk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class AnswersMapperTest {
    @Autowired
    private AnswersMapper answersMapper;

    @Test
    public void testFind() throws Exception {
        final AnswerInterface answer = this.answersMapper.find(1);

        Assert.assertEquals((long) 1, (long) answer.getId());
        Assert.assertEquals("Answer1", answer.getTxt());
        Assert.assertEquals((long) 1, (long) answer.getQuestionId());
        Assert.assertEquals(true, answer.getIsRight());
    }

    @Test
    public void testDelete() throws Exception {
        Answer answer = this.answersMapper.find(1);
        Assert.assertNotNull(answer);
        this.answersMapper.delete(answer);
        answer = this.answersMapper.find(1);
        Assert.assertNull(answer);
    }
}
