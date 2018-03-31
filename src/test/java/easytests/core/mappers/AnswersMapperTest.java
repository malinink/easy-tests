package easytests.core.mappers;

import easytests.core.entities.AnswerEntity;
import easytests.support.AnswersSupport;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author anyamaz
 */
public class AnswersMapperTest extends AbstractMapperTest {

    private AnswersSupport answersSupport = new AnswersSupport();

    @Autowired
    private AnswersMapper answersMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<AnswerEntity> answersFoundedEntities = this.answersMapper.findAll();

        Assert.assertEquals(3, answersFoundedEntities.size());
        Integer index = 0;
        for (AnswerEntity answerEntity : answersFoundedEntities) {
            final AnswerEntity answerFixtureEntity = this.answersSupport.getEntityFixtureMock(index);
            this.answersSupport.assertEquals(answerFixtureEntity, answerEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final AnswerEntity answerFixtureEntity = this.answersSupport.getEntityFixtureMock(0);

        final AnswerEntity answerFoundedEntity = this.answersMapper.find(answerFixtureEntity.getId());

        this.answersSupport.assertEquals(answerFixtureEntity, answerFoundedEntity);
    }

    @Test
    public void testFindByQuestionId() throws Exception {
        final List<AnswerEntity> answerFixtureEntities = new ArrayList<>();
        answerFixtureEntities.add(this.answersSupport.getEntityFixtureMock(0));
        answerFixtureEntities.add(this.answersSupport.getEntityFixtureMock(2));

        final List<AnswerEntity> answerFoundedEntities = this.answersMapper.findByQuestionId(1);

        Assert.assertEquals(2, answerFoundedEntities.size());
        Integer index = 0;
        for (AnswerEntity answerEntity : answerFoundedEntities) {
            this.answersSupport.assertEquals(answerFixtureEntities.get(index), answerEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final AnswerEntity answerUnidentifiedEntity = this.answersSupport.getEntityAdditionalMock(0);

        this.answersMapper.insert(answerUnidentifiedEntity);

        verify(answerUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());
        final AnswerEntity answerInsertedEntity = this.answersMapper.find(id.getValue());
        Assert.assertNotNull(answerInsertedEntity);
        this.answersSupport.assertEqualsWithoutId(answerUnidentifiedEntity, answerInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final AnswerEntity answerChangedEntity = this.answersSupport.getEntityAdditionalMock(2);
        final AnswerEntity answerBeforeUpdateEntity = this.answersMapper.find(answerChangedEntity.getId());
        Assert.assertNotNull(answerBeforeUpdateEntity);
        this.answersSupport.assertNotEqualsWithoutId(answerChangedEntity, answerBeforeUpdateEntity);

        this.answersMapper.update(answerChangedEntity);

        final AnswerEntity answerFoundedEntity = this.answersMapper.find(answerChangedEntity.getId());
        this.answersSupport.assertEquals(answerChangedEntity, answerFoundedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.answersSupport.getEntityFixtureMock(0).getId();
        final AnswerEntity answerFoundedEntity = this.answersMapper.find(id);
        Assert.assertNotNull(answerFoundedEntity);

        this.answersMapper.delete(answerFoundedEntity);

        Assert.assertNull(this.answersMapper.find(0));
    }
}
