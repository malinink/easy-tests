package easytests.core.mappers;

import easytests.core.entities.QuizEntity;
import easytests.support.QuizzesSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * @author miron97
 */
public class QuizzesMapperTest extends AbstractMapperTest {

    private QuizzesSupport quizzesSupport = new QuizzesSupport();

    @Autowired
    private QuizzesMapper quizzesMapper;

    @Test
    public void testFindAll() throws Exception {

        final List<QuizEntity> quizFoundedEntities = this.quizzesMapper.findAll();

        Assert.assertNotNull(quizFoundedEntities);
        Assert.assertEquals((long) 3, (long) quizFoundedEntities.size());

        Integer index = 0;
        for(QuizEntity quizEntity: quizFoundedEntities){
            final QuizEntity quizFixtureEntity = this.quizzesSupport.getEntityFixtureMock(index);

            this.quizzesSupport.assertEquals(quizFixtureEntity, quizEntity);
            index++;
        }

    }

    @Test
    public void testFind() throws Exception {

        final QuizEntity quizFixtureEntity = this.quizzesSupport.getEntityFixtureMock(1);
        final QuizEntity quizFoundedEntity = this.quizzesMapper.find(quizFixtureEntity.getId());

        this.quizzesSupport.assertEquals(quizFixtureEntity, quizFoundedEntity);
    }

    @Test
    public void testFindByIssueId() throws Exception {
        final List<QuizEntity> quizzesFixtureEntities = new ArrayList<>();
        quizzesFixtureEntities.add(this.quizzesSupport.getEntityFixtureMock(1));

        final List<QuizEntity> quizzesFoundedEntities = this.quizzesMapper.findByIssueId(2);

        Assert.assertEquals(1, quizzesFoundedEntities.size());
        this.quizzesSupport.assertEquals(quizzesFixtureEntities.get(0), quizzesFoundedEntities.get(0));

    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final QuizEntity quizUnidentifiedEntity = this.quizzesSupport.getEntityAdditionalMock(0);

        quizzesMapper.insert(quizUnidentifiedEntity);

        verify(quizUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final QuizEntity quizInsertedEntity = quizzesMapper.find(id.getValue());

        this.quizzesSupport.assertEqualsWithoutId(quizUnidentifiedEntity, quizInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {

        final QuizEntity quizChangedEntity = this.quizzesSupport.getEntityAdditionalMock(1);
        final QuizEntity quizBeforeUpdateEntity = this.quizzesMapper.find(quizChangedEntity.getId());

        Assert.assertNotNull(quizBeforeUpdateEntity);
        this.quizzesSupport.assertNotEqualsWithoutIds(quizChangedEntity, quizBeforeUpdateEntity);

        this.quizzesMapper.update(quizChangedEntity);
        final QuizEntity quizUpdatedEntity = this.quizzesMapper.find(quizChangedEntity.getId());

        this.quizzesSupport.assertEquals(quizChangedEntity, quizUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.quizzesSupport.getEntityFixtureMock(0).getId();
        QuizEntity quizFoundedEntity = this.quizzesMapper.find(id);

        Assert.assertNotNull(quizFoundedEntity);

        this.quizzesMapper.delete(quizFoundedEntity);
        quizFoundedEntity = this.quizzesMapper.find(id);
        Assert.assertNull(quizFoundedEntity);
    }}
