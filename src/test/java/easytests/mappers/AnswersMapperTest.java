package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.AnswerEntity;
import easytests.models.QuestionModelInterface;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
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
    public void testFindAll() throws Exception {
        final List<AnswerEntity> answersEntities = this.answersMapper.findAll();
        Assert.assertEquals((long) 3, (long) answersEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final AnswerEntity answer = this.answersMapper.find(1);
        Assert.assertEquals((Integer) 1, answer.getId());
        Assert.assertEquals("Answer1", answer.getTxt());
        Assert.assertEquals((Integer) 1, answer.getQuestionId());
        Assert.assertEquals(true, answer.getRight());
    }

    @Test
    public void testInsert() throws Exception {
        final Integer id = this.answersMapper.findAll().size() + 1;
        final String txt = "Text";
        final Integer questionId = 1;
        final Boolean right = true;

        AnswerEntity answerEntity = Mockito.mock(AnswerEntity.class);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.getRight()).thenReturn(right);

        this.answersMapper.insert(answerEntity);

        verify(answerEntity, times(1)).setId(id);

        answerEntity = this.answersMapper.find(id);
        Assert.assertEquals(id, answerEntity.getId());
        Assert.assertEquals(txt, answerEntity.getTxt());
        Assert.assertEquals(questionId, answerEntity.getQuestionId());
        Assert.assertEquals(right, answerEntity.getRight());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String txt = "Answer1";
        final Integer questionId = 1;
        final Boolean right = true;

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);

        AnswerEntity answerEntity = this.answersMapper.find(id);
        Assert.assertNotNull(answerEntity);
        Assert.assertEquals(id, answerEntity.getId());
        Assert.assertEquals(txt, answerEntity.getTxt());
        Assert.assertEquals(questionId, answerEntity.getQuestionId());
        Assert.assertEquals(right, answerEntity.getRight());

        answerEntity = Mockito.mock(AnswerEntity.class);
        Mockito.when(answerEntity.getId()).thenReturn(id);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.getRight()).thenReturn(right);

        this.answersMapper.update(answerEntity);

        answerEntity = this.answersMapper.find(id);
        Assert.assertEquals(id, answerEntity.getId());
        Assert.assertEquals(txt, answerEntity.getTxt());
        Assert.assertEquals(questionId, answerEntity.getQuestionId());
        Assert.assertEquals(right, answerEntity.getRight());
    }

    @Test
    public void testDelete() throws Exception {
        AnswerEntity answerEntity = this.answersMapper.find(1);
        Assert.assertNotNull(answerEntity);

        this.answersMapper.delete(answerEntity);
        answerEntity = this.answersMapper.find(1);
        Assert.assertNull(answerEntity);
    }
}
