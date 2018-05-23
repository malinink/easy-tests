package easytests.core.mappers;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import easytests.support.IssueStandardTopicPrioritySupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SakhPrace
 */
public class IssueStandardTopicPrioritiesMapperTest extends AbstractMapperTest {

    protected IssueStandardTopicPrioritySupport issueStandardTopicPrioritySupport = new IssueStandardTopicPrioritySupport();

    @Autowired
    private IssueStandardTopicPrioritiesMapper issueStandardTopicPrioritiesMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityFoundedEntities = this.issueStandardTopicPrioritiesMapper.findAll();

        Assert.assertEquals(3, issueStandardTopicPriorityFoundedEntities.size());
        Integer index = 0;
        for (IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity : issueStandardTopicPriorityFoundedEntities) {
            final IssueStandardTopicPriorityEntity issueStandardTopicPriorityFixtureEntity = this.issueStandardTopicPrioritySupport.getEntityFixtureMock(index);
            this.issueStandardTopicPrioritySupport.assertEquals(issueStandardTopicPriorityFixtureEntity, issueStandardTopicPriorityEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityFixtureEntity = this.issueStandardTopicPrioritySupport.getEntityFixtureMock(0);

        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityFoundedEntity = this.issueStandardTopicPrioritiesMapper.find(1);

        this.issueStandardTopicPrioritySupport.assertEquals(issueStandardTopicPriorityFixtureEntity, issueStandardTopicPriorityFoundedEntity);
    }

    @Test
    public void testFindByIssueStandard() throws Exception {
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityFixtureEntities = new ArrayList<>();
        issueStandardTopicPriorityFixtureEntities.add(this.issueStandardTopicPrioritySupport.getEntityFixtureMock(0));
        issueStandardTopicPriorityFixtureEntities.add(this.issueStandardTopicPrioritySupport.getEntityFixtureMock(1));

        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityFoundedEntities
                = this.issueStandardTopicPrioritiesMapper.findByIssueStandardId(1);

        Assert.assertEquals(2, issueStandardTopicPriorityFoundedEntities.size());
        Integer index = 0;
        for (IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity : issueStandardTopicPriorityFoundedEntities) {
            this.issueStandardTopicPrioritySupport.assertEquals(issueStandardTopicPriorityFixtureEntities.get(index), issueStandardTopicPriorityEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityUnidentifiedEntity = this.issueStandardTopicPrioritySupport.getEntityAdditionalMock(0);

        this.issueStandardTopicPrioritiesMapper.insert(issueStandardTopicPriorityUnidentifiedEntity);

        verify(issueStandardTopicPriorityUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityInsertedEntity = this.issueStandardTopicPrioritiesMapper.find(id.getValue());
        Assert.assertNotNull(issueStandardTopicPriorityInsertedEntity);
        this.issueStandardTopicPrioritySupport.assertEqualsWithoutId(issueStandardTopicPriorityUnidentifiedEntity, issueStandardTopicPriorityInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityChangedEntity = this.issueStandardTopicPrioritySupport.getEntityAdditionalMock(1);
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityBeforeUpdateEntity = this.issueStandardTopicPrioritiesMapper.find(issueStandardTopicPriorityChangedEntity.getId());
        Assert.assertNotNull(issueStandardTopicPriorityBeforeUpdateEntity);
        this.issueStandardTopicPrioritySupport.assertNotEqualsWithoutId(issueStandardTopicPriorityChangedEntity, issueStandardTopicPriorityBeforeUpdateEntity);

        this.issueStandardTopicPrioritiesMapper.update(issueStandardTopicPriorityChangedEntity);

        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityUpdatedEntity = this.issueStandardTopicPrioritiesMapper.find(issueStandardTopicPriorityChangedEntity.getId());
        this.issueStandardTopicPrioritySupport.assertEquals(issueStandardTopicPriorityChangedEntity, issueStandardTopicPriorityUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.issueStandardTopicPrioritySupport.getEntityFixtureMock(0).getId();
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityFoundedEntity = this.issueStandardTopicPrioritiesMapper.find(id);
        Assert.assertNotNull(issueStandardTopicPriorityFoundedEntity);

        this.issueStandardTopicPrioritiesMapper.delete(issueStandardTopicPriorityFoundedEntity);

        Assert.assertNull(this.issueStandardTopicPrioritiesMapper.find(id));
    }
}
