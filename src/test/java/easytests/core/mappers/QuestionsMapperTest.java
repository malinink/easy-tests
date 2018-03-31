package easytests.core.mappers;

import easytests.core.entities.QuestionEntity;
import easytests.support.QuestionsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author RisaMagpie
 */
public class QuestionsMapperTest extends AbstractMapperTest {

    protected QuestionsSupport questionsSupport = new QuestionsSupport();

    @Autowired
    private QuestionsMapper questionsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<QuestionEntity> questionsFoundedEntities = this.questionsMapper.findAll();
        Assert.assertEquals( 3,  questionsFoundedEntities.size());

        Integer index = 0;
        for (QuestionEntity questionEntity: questionsFoundedEntities) {
            final QuestionEntity questionFixtureEntity = this.questionsSupport.getEntityFixtureMock(index);

            this.questionsSupport.assertEquals(questionFixtureEntity, questionEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final QuestionEntity questionFixtureEntity = this.questionsSupport.getEntityFixtureMock(0);

        final QuestionEntity questionFoundedEntity = this.questionsMapper.find(1);

        this.questionsSupport.assertEquals(questionFixtureEntity, questionFoundedEntity);
    }

    @Test
    public void testFindByTopicId() throws Exception {
        final List<QuestionEntity> questionsFixtureEntities = new ArrayList<>();

        questionsFixtureEntities.add(this.questionsSupport.getEntityFixtureMock(2));

        final List<QuestionEntity> questionsFoundedEntities = this.questionsMapper.findByTopicId(2);

        Assert.assertEquals(1, questionsFoundedEntities.size());

        Integer index = 0;
        for (QuestionEntity questionEntity: questionsFoundedEntities) {
            this.questionsSupport.assertEquals(questionsFixtureEntities.get(index), questionEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final QuestionEntity questionUnidentifiedEntity = this.questionsSupport.getEntityAdditionalMock(0);

        this.questionsMapper.insert(questionUnidentifiedEntity);

        verify(questionUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final QuestionEntity questionInsertedEntity = this.questionsMapper.find(id.getValue());

        Assert.assertNotNull(questionInsertedEntity);
        this.questionsSupport.assertEqualsWithoutId(questionUnidentifiedEntity, questionInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final QuestionEntity questionChangedEntity = this.questionsSupport.getEntityAdditionalMock(1);
        final QuestionEntity questionBeforeUpdateEntity = this.questionsMapper.find(questionChangedEntity.getId());

        Assert.assertNotNull(questionBeforeUpdateEntity);
        this.questionsSupport.assertNotEqualsWithoutId(questionChangedEntity, questionBeforeUpdateEntity);

        this.questionsMapper.update(questionChangedEntity);
        final QuestionEntity questionUpdatedEntity = this.questionsMapper.find(questionChangedEntity.getId());

        this.questionsSupport.assertEquals(questionChangedEntity, questionUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.questionsSupport.getEntityFixtureMock(0).getId();
        final QuestionEntity questionFoundedEntity = this.questionsMapper.find(id);

        Assert.assertNotNull(questionFoundedEntity);

        this.questionsMapper.delete(questionFoundedEntity);

        Assert.assertNull(this.questionsMapper.find(id));
    }
}
