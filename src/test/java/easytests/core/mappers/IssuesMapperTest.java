package easytests.core.mappers;

import easytests.core.entities.IssueEntity;
import easytests.support.IssueSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * @author greenbarrow
 */
public class IssuesMapperTest extends AbstractMapperTest {

    private IssueSupport issueSupport = new IssueSupport();

    @Autowired
    private IssuesMapper issuesMapper;


    @Test
    public void testFindAll() throws Exception {
        final List<IssueEntity> issuesFoundedEntities = this.issuesMapper.findAll();

        Assert.assertEquals(3, issuesFoundedEntities.size());
        Integer index = 0;
        for (IssueEntity issueEntity : issuesFoundedEntities) {
            final IssueEntity issueFixtureEntity = this.issueSupport.getEntityFixtureMock(index);
            this.issueSupport.assertEquals(issueFixtureEntity, issueEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final IssueEntity issueFixtureEntity = this.issueSupport.getEntityFixtureMock(0);

        final IssueEntity issueFoundedEntity = this.issuesMapper.find(issueFixtureEntity.getId());

        this.issueSupport.assertEquals(issueFixtureEntity, issueFoundedEntity);
    }

    @Test
    public void testFindBySubjectId() throws Exception {
        final List<IssueEntity> issueFixtureEntities = new ArrayList<>();
        issueFixtureEntities.add(this.issueSupport.getEntityFixtureMock(0));

        final List<IssueEntity> issueFoundedEntities = this.issuesMapper.findBySubjectId(issueFixtureEntities.get(0).getSubjectId());

        Integer index = 0;
        for (IssueEntity issueEntity : issueFoundedEntities) {
            this.issueSupport.assertEquals(issueFixtureEntities.get(index), issueEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final IssueEntity issueUnidentifiedEntity = this.issueSupport.getEntityAdditionalMock(0);

        this.issuesMapper.insert(issueUnidentifiedEntity);

        verify(issueUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());
        final IssueEntity issueInsertedEntity = this.issuesMapper.find(id.getValue());
        Assert.assertNotNull(issueInsertedEntity);
        this.issueSupport.assertEqualsWithoutId(issueUnidentifiedEntity, issueInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final IssueEntity issueChangedEntity = this.issueSupport.getEntityAdditionalMock(1);
        final IssueEntity issueBeforeUpdateEntity = this.issuesMapper.find(issueChangedEntity.getId());
        Assert.assertNotNull(issueBeforeUpdateEntity);
        this.issueSupport.assertNotEqualsWithoutId(issueChangedEntity, issueBeforeUpdateEntity);

        this.issuesMapper.update(issueChangedEntity);

        final IssueEntity issueFoundedEntity = this.issuesMapper.find(issueChangedEntity.getId());
        this.issueSupport.assertEquals(issueChangedEntity, issueFoundedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.issueSupport.getEntityFixtureMock(0).getId();
        final IssueEntity issueFoundedEntity = this.issuesMapper.find(id);
        Assert.assertNotNull(issueFoundedEntity);

        this.issuesMapper.delete(issueFoundedEntity);

        Assert.assertNull(this.issuesMapper.find(id));
    }

}
