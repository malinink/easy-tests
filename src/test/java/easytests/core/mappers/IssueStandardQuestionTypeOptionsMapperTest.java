package easytests.core.mappers;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionsMapperTest extends AbstractMapperTest {

    @Autowired
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = this.questionTypeOptionMapper.findAll();

        Assert.assertNotNull(questionTypeOptionEntities);
        Assert.assertEquals(5, questionTypeOptionEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.questionTypeOptionMapper.find(1);

        Assert.assertEquals((Integer) 1, questionTypeOptionEntity.getId());
        Assert.assertEquals((Integer) 1, questionTypeOptionEntity.getQuestionTypeId());
        Assert.assertEquals((Integer) 1, questionTypeOptionEntity.getMinQuestions());
        Assert.assertEquals(null, questionTypeOptionEntity.getMaxQuestions());
        Assert.assertEquals(null, questionTypeOptionEntity.getTimeLimit());
        Assert.assertEquals((Integer) 1, questionTypeOptionEntity.getIssueStandardId());
    }

    @Test
    public void testFindByIssueStandard() throws Exception {
        Integer issueStandardId = 1;

        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities
                = this.questionTypeOptionMapper.findByIssueStandardId(issueStandardId);

        Assert.assertEquals(3, questionTypeOptionEntities.size());

        IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = questionTypeOptionEntities.get(2);
        Assert.assertEquals((Integer) 3, questionTypeOptionEntity.getId());
        Assert.assertEquals((Integer) 3, questionTypeOptionEntity.getQuestionTypeId());
        Assert.assertEquals((Integer) 5, questionTypeOptionEntity.getMinQuestions());
        Assert.assertEquals((Integer) 10, questionTypeOptionEntity.getMaxQuestions());
        Assert.assertEquals(null, questionTypeOptionEntity.getTimeLimit());
        Assert.assertEquals((Integer) 1, questionTypeOptionEntity.getIssueStandardId());
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final Integer questionTypeId = 2;
        final Integer minQuestions = 10;
        final Integer maxQuestions = 20;
        final Integer timeLimit = 100;
        final Integer issueStandardId = 2;

        IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = Mockito.mock(IssueStandardQuestionTypeOptionEntity.class);

        Mockito.when(questionTypeOptionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionTypeOptionEntity.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(questionTypeOptionEntity.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(questionTypeOptionEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(questionTypeOptionEntity.getIssueStandardId()).thenReturn(issueStandardId);

        this.questionTypeOptionMapper.insert(questionTypeOptionEntity);

        verify(questionTypeOptionEntity, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        questionTypeOptionEntity = this.questionTypeOptionMapper.find(id.getValue());
        Assert.assertNotNull(questionTypeOptionEntity);
        Assert.assertEquals(id.getValue(), questionTypeOptionEntity.getId());
        Assert.assertEquals(questionTypeId, questionTypeOptionEntity.getQuestionTypeId());
        Assert.assertEquals(minQuestions, questionTypeOptionEntity.getMinQuestions());
        Assert.assertEquals(maxQuestions, questionTypeOptionEntity.getMaxQuestions());
        Assert.assertEquals(timeLimit, questionTypeOptionEntity.getTimeLimit());
        Assert.assertEquals(issueStandardId, questionTypeOptionEntity.getIssueStandardId());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 4;
        final Integer questionTypeId = 2;
        final Integer minQuestions = 10;
        final Integer maxQuestions = 20;
        final Integer timeLimit = 100;
        final Integer issueStandardId = 2;

        IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.questionTypeOptionMapper.find(id);
        Assert.assertNotNull(questionTypeOptionEntity);
        Assert.assertEquals(id, questionTypeOptionEntity.getId());
        Assert.assertNotEquals(questionTypeId, questionTypeOptionEntity.getQuestionTypeId());
        Assert.assertNotEquals(minQuestions, questionTypeOptionEntity.getMinQuestions());
        Assert.assertNotEquals(maxQuestions, questionTypeOptionEntity.getMaxQuestions());
        Assert.assertNotEquals(timeLimit, questionTypeOptionEntity.getTimeLimit());
        Assert.assertEquals(issueStandardId, questionTypeOptionEntity.getIssueStandardId());

        questionTypeOptionEntity = Mockito.mock(IssueStandardQuestionTypeOptionEntity.class);
        Mockito.when(questionTypeOptionEntity.getId()).thenReturn(id);
        Mockito.when(questionTypeOptionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionTypeOptionEntity.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(questionTypeOptionEntity.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(questionTypeOptionEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(questionTypeOptionEntity.getIssueStandardId()).thenReturn(issueStandardId);

        this.questionTypeOptionMapper.update(questionTypeOptionEntity);

        questionTypeOptionEntity = this.questionTypeOptionMapper.find(id);
        Assert.assertEquals(id, questionTypeOptionEntity.getId());
        Assert.assertEquals(questionTypeId, questionTypeOptionEntity.getQuestionTypeId());
        Assert.assertEquals(minQuestions, questionTypeOptionEntity.getMinQuestions());
        Assert.assertEquals(maxQuestions, questionTypeOptionEntity.getMaxQuestions());
        Assert.assertEquals(timeLimit, questionTypeOptionEntity.getTimeLimit());
        Assert.assertEquals(issueStandardId, questionTypeOptionEntity.getIssueStandardId());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity = this.questionTypeOptionMapper.find(id);
        Assert.assertNotNull(questionTypeOptionEntity);

        this.questionTypeOptionMapper.delete(questionTypeOptionEntity);
        questionTypeOptionEntity = this.questionTypeOptionMapper.find(id);
        Assert.assertNull(questionTypeOptionEntity);
    }
}
