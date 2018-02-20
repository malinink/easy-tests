package easytests.core.mappers;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.support.IssueStandardQuestionTypeOptionsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionsMapperTest extends AbstractMapperTest {

    protected IssueStandardQuestionTypeOptionsSupport issueStandardQuestionTypeOptionsSupport = new IssueStandardQuestionTypeOptionsSupport();

    @Autowired
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionFoundedEntities = this.questionTypeOptionMapper.findAll();

        Assert.assertEquals(5, questionTypeOptionFoundedEntities.size());

        Integer index = 0;
        for (IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity : questionTypeOptionFoundedEntities) {
            final IssueStandardQuestionTypeOptionEntity questionTypeOptionFixtureEntity = this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(index);

            this.issueStandardQuestionTypeOptionsSupport.assertEquals(questionTypeOptionFixtureEntity, questionTypeOptionEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionFixtureEntity = this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(0);

        final IssueStandardQuestionTypeOptionEntity questionTypeOptionFoundedEntity = this.questionTypeOptionMapper.find(1);

        this.issueStandardQuestionTypeOptionsSupport.assertEquals(questionTypeOptionFixtureEntity, questionTypeOptionFoundedEntity);

    }

    @Test
    public void testFindByIssueStandard() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionFixtureEntities = new ArrayList<>();
        questionTypeOptionFixtureEntities.add(this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(0));
        questionTypeOptionFixtureEntities.add(this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(1));
        questionTypeOptionFixtureEntities.add(this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(2));

        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionFoundedEntities
                = this.questionTypeOptionMapper.findByIssueStandardId(1);

        Assert.assertEquals(3, questionTypeOptionFoundedEntities.size());

        Integer index = 0;
        for (IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity : questionTypeOptionFoundedEntities) {
            this.issueStandardQuestionTypeOptionsSupport.assertEquals(questionTypeOptionFixtureEntities.get(index), questionTypeOptionEntity);
            index++;

        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionUnidentifiedEntity = this.issueStandardQuestionTypeOptionsSupport.getEntityAdditionalMock(0);

        this.questionTypeOptionMapper.insert(questionTypeOptionUnidentifiedEntity);


        verify(questionTypeOptionUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final IssueStandardQuestionTypeOptionEntity questionTypeOptionInsertedEntity = this.questionTypeOptionMapper.find(id.getValue());

        Assert.assertNotNull(questionTypeOptionInsertedEntity);
        this.issueStandardQuestionTypeOptionsSupport.assertEqualsWithoutId(questionTypeOptionUnidentifiedEntity, questionTypeOptionInsertedEntity);

    }

    @Test
    public void testUpdate() throws Exception {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionChangedEntity = this.issueStandardQuestionTypeOptionsSupport.getEntityAdditionalMock(1);
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionBeforeUpdateEntity = this.questionTypeOptionMapper.find(questionTypeOptionChangedEntity.getId());

        Assert.assertNotNull(questionTypeOptionBeforeUpdateEntity);
        this.issueStandardQuestionTypeOptionsSupport.assertNotEqualsWithoutId(questionTypeOptionChangedEntity,questionTypeOptionBeforeUpdateEntity);

        this.questionTypeOptionMapper.update(questionTypeOptionChangedEntity);
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionUpdatedEntity = this.questionTypeOptionMapper.find(questionTypeOptionChangedEntity.getId());

        this.issueStandardQuestionTypeOptionsSupport.assertEquals(questionTypeOptionChangedEntity, questionTypeOptionUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(0).getId();
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionFoundedEntity = this.questionTypeOptionMapper.find(id);

        Assert.assertNotNull(questionTypeOptionFoundedEntity);

        this.questionTypeOptionMapper.delete(questionTypeOptionFoundedEntity);

        Assert.assertNull(this.questionTypeOptionMapper.find(id));
    }

}
