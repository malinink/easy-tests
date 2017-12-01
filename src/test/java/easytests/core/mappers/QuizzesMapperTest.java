package easytests.core.mappers;

import easytests.core.entities.QuizEntity;
import java.time.LocalDateTime;
import java.util.List;

import easytests.support.QuizzesSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author miron97
 */
public class QuizzesMapperTest extends AbstractMapperTest {

    private QuizzesSupport quizzesSupport = new QuizzesSupport();

    @Autowired
    private QuizzesMapper quizzesMapper;

    @Test
    public void testFindAll() throws Exception {

        final List<QuizEntity> quizEntities = this.quizzesMapper.findAll();

        Assert.assertNotNull(quizEntities);
        Assert.assertEquals((long) 3, (long) quizEntities.size());

        Integer index = 0;
        for(QuizEntity quizEntity:quizEntities){
            final QuizEntity quizFixtureEntity= this.quizzesSupport.getEntityFixtureMock(index);

            this.quizzesSupport.assertEquals(quizFixtureEntity, quizEntity);
            index++;
        }

    }

    @Test
    public void testFind() throws Exception {

        final QuizEntity quizFixtureEntity = this.quizzesSupport.getEntityFixtureMock(1);
        final QuizEntity quizEntity = this.quizzesMapper.find(quizFixtureEntity.getId());

        this.quizzesSupport.assertEquals(quizFixtureEntity, quizEntity);
    }



    @Test
    public void testIssueNotNull() throws Exception {

        final List<QuizEntity> quizEntities = this.quizzesMapper.findByIssueId(4);

        Assert.assertNotNull(quizEntities);
        Assert.assertEquals(0, quizEntities.size());

    }

    @Test
    public void testFindByIssueId() throws Exception {

        final QuizEntity quizFixtureEntity = this.quizzesSupport.getEntityFixtureMock(1);
        final List<QuizEntity> quizEntities = this.quizzesMapper.findByIssueId(quizFixtureEntity.getIssueId());

        for(QuizEntity quizEntity:quizEntities){
            this.quizzesSupport.assertEquals(quizFixtureEntity, quizEntity);
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final QuizEntity quizAdditionalEntity = this.quizzesSupport.getEntityAdditionalMock(0);

        quizzesMapper.insert(quizAdditionalEntity);

        verify(quizAdditionalEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final QuizEntity quizInsertedEntity = quizzesMapper.find(id.getValue());

        this.quizzesSupport.assertEqualsWithoutId(quizAdditionalEntity, quizInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {

        final QuizEntity quizAdditionalEntity = this.quizzesSupport.getEntityAdditionalMock(1);
        final Integer id = quizAdditionalEntity.getId();
        final QuizEntity quizEntity = this.quizzesMapper.find(id);

        Assert.assertNotNull(quizEntity);
        this.quizzesSupport.assertNotEqualsWithoutIds(quizAdditionalEntity, quizEntity);

        this.quizzesMapper.update(quizAdditionalEntity);
        final QuizEntity quizUpdatedEntity = this.quizzesMapper.find(id);

        this.quizzesSupport.assertEquals(quizAdditionalEntity, quizUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.quizzesSupport.getEntityFixtureMock(0).getId();
        QuizEntity quizEntity = this.quizzesMapper.find(id);

        Assert.assertNotNull(quizEntity);

        this.quizzesMapper.delete(quizEntity);
        quizEntity = this.quizzesMapper.find(id);
        Assert.assertNull(quizEntity);
    }}
