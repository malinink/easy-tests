package easytests.core.mappers;

import easytests.core.entities.AnswerEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author malinink
 */
public class AnswersMapperTest extends AbstractMapperTest {

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
    public void testFindByQuestionId() throws Exception {
        final List<AnswerEntity> answers = this.answersMapper.findByQuestionId(1);
        Assert.assertEquals((Integer) 1, (Integer) answers.size());
        final AnswerEntity answer = answers.get(0);

        Assert.assertEquals((Integer) 1, answer.getId());
        Assert.assertEquals("Answer1", answer.getTxt());
        Assert.assertEquals((Integer) 1, answer.getQuestionId());
        Assert.assertEquals(true, answer.getRight());
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final String txt = "Text";
        final Integer questionId = 1;
        final Boolean right = true;

        AnswerEntity answerEntity = Mockito.mock(AnswerEntity.class);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.getRight()).thenReturn(right);

        this.answersMapper.insert(answerEntity);

        verify(answerEntity, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        answerEntity = this.answersMapper.find(id.getValue());
        Assert.assertNotNull(answerEntity);
        Assert.assertEquals(id.getValue(), answerEntity.getId());
        Assert.assertEquals(txt, answerEntity.getTxt());
        Assert.assertEquals(questionId, answerEntity.getQuestionId());
        Assert.assertEquals(right, answerEntity.getRight());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String txt = "NewAnswer";
        final Integer questionId = 2;
        final Boolean right = false;

        AnswerEntity answerEntity = this.answersMapper.find(id);
        Assert.assertNotNull(answerEntity);
        Assert.assertEquals(id, answerEntity.getId());
        Assert.assertNotEquals(txt, answerEntity.getTxt());
        Assert.assertNotEquals(questionId, answerEntity.getQuestionId());
        Assert.assertNotEquals(right, answerEntity.getRight());

        answerEntity = Mockito.mock(AnswerEntity.class);
        Mockito.when(answerEntity.getId()).thenReturn(id);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.getRight()).thenReturn(right);

        this.answersMapper.update(answerEntity);

        answerEntity = this.answersMapper.find(id);
        Assert.assertNotNull(answerEntity);
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
