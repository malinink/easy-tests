package easytests.core.mappers;

import easytests.core.entities.AnswerEntity;
import easytests.support.AnswersSupport;
import java.util.Arrays;
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
        for (AnswerEntity answerEntity: answersFoundedEntities) {
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
        final List<AnswerEntity> answerFixtureEntityList = Arrays.asList(this.answersSupport.getEntityFixtureMock(0), this.answersSupport.getEntityFixtureMock(2));
        final List<AnswerEntity> answerFoundedEntityList = this.answersMapper.findByQuestionId(answerFixtureEntityList.get(0).getQuestionId());
        for(int i = 0; i < answerFixtureEntityList.size(); i++)
            this.answersSupport.assertEquals(answerFixtureEntityList.get(i), answerFoundedEntityList.get(i));
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
        final AnswerEntity answerChangedEntity = this.answersSupport.getEntityAdditionalMock(1);
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
