package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.QuestionEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void testFindAll() throws Exception {
        final List<QuestionEntity> questionsEntities = this.questionsMapper.findAll();
        Assert.assertEquals((long) 3, (long) questionsEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final QuestionEntity question = this.questionsMapper.find(1);
        Assert.assertEquals((long) 1, (long) question.getId());
        Assert.assertEquals("test1", question.getText());
        Assert.assertEquals((long) 1, (long) question.getType());
        Assert.assertEquals((long) 1, (long) question.getTopicId());
    }

    @Test
    public void testFindByTopicId() throws Exception {
        final List<QuestionEntity> questions = this.questionsMapper.findByTopicId(2);
        Assert.assertEquals((Integer) 1, (Integer) questions.size());
        final QuestionEntity question = questions.get(0);

        Assert.assertEquals((Integer) 3, question.getId());
        Assert.assertEquals("test3", question.getText());
        Assert.assertEquals((Integer) 3, question.getType());
        Assert.assertEquals((Integer) 2, question.getTopicId());
    }

    @Test
    public void testInsert() throws Exception {
        final Integer id = this.questionsMapper.findAll().size() + 1;
        final String text = "text1";
        final Integer type = 1;
        final Integer topicId = 1;

        QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getType()).thenReturn(type);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);

        this.questionsMapper.insert(questionEntity);

        verify(questionEntity, times(1)).setId(id);

        questionEntity = this.questionsMapper.find(id);
        Assert.assertEquals(id, questionEntity.getId());
        Assert.assertEquals(text, questionEntity.getText());
        Assert.assertEquals(type, questionEntity.getType());
        Assert.assertEquals(topicId, questionEntity.getTopicId());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String text = "text2";
        final Integer type = 2;
        final Integer topicId = 2;

        QuestionEntity questionEntity = this.questionsMapper.find(id);
        Assert.assertNotNull(questionEntity);
        Assert.assertEquals(id, questionEntity.getId());
        Assert.assertNotEquals(text, questionEntity.getText());
        Assert.assertNotEquals(type, questionEntity.getType());
        Assert.assertNotEquals(topicId, questionEntity.getTopicId());

        questionEntity = Mockito.mock(QuestionEntity.class);
        Mockito.when(questionEntity.getId()).thenReturn(id);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getType()).thenReturn(type);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);

        this.questionsMapper.update(questionEntity);

        questionEntity = this.questionsMapper.find(id);
        Assert.assertEquals(id, questionEntity.getId());
        Assert.assertEquals(text, questionEntity.getText());
        Assert.assertEquals(type, questionEntity.getType());
        Assert.assertEquals(topicId, questionEntity.getTopicId());
    }

    @Test
    public void testDelete() throws Exception {
        QuestionEntity questionEntity = this.questionsMapper.find(1);
        Assert.assertNotNull(questionEntity);

        this.questionsMapper.delete(questionEntity);
        questionEntity = this.questionsMapper.find(1);
        Assert.assertNull(questionEntity);
    }
}
