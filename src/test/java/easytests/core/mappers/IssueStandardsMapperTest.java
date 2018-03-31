package easytests.core.mappers;

import easytests.core.entities.IssueStandardEntity;
import easytests.support.IssueStandardSupport;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Yarik2308
 */
public class IssueStandardsMapperTest extends AbstractMapperTest {

    protected IssueStandardSupport issueStandardSupport = new IssueStandardSupport();

    @Autowired
    private IssueStandardsMapper issueStandardsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<IssueStandardEntity> issueStandardsFoundedEntities = this.issueStandardsMapper.findAll();

        Assert.assertEquals(2, issueStandardsFoundedEntities.size());
        Integer index = 0;
        for (IssueStandardEntity issueStandardEntity : issueStandardsFoundedEntities) {
            final IssueStandardEntity issueStandartFixtureEntity = this.issueStandardSupport.getEntityFixtureMock(index);
            this.issueStandardSupport.assertEquals(issueStandartFixtureEntity, issueStandardEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final IssueStandardEntity issueStandardFixtureEntity = this.issueStandardSupport.getEntityFixtureMock(0);

        final IssueStandardEntity issueStandardFoundedEntity = this.issueStandardsMapper.find(1);

        this.issueStandardSupport.assertEquals(issueStandardFixtureEntity, issueStandardFoundedEntity);
    }

    @Test
    public void testFindBySubjectId() throws Exception {
        final IssueStandardEntity issueStandardFixtireEntity = this.issueStandardSupport.getEntityFixtureMock(1);

        final IssueStandardEntity issueStandardFoundedEntity = this.issueStandardsMapper.findBySubjectId(3);

        this.issueStandardSupport.assertEquals(issueStandardFixtireEntity, issueStandardFoundedEntity);
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final IssueStandardEntity issueStandardUnidentifiedEntity = this.issueStandardSupport.getEntityAdditionalMock(0);

        this.issueStandardsMapper.insert(issueStandardUnidentifiedEntity);

        verify(issueStandardUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());
        final IssueStandardEntity issueStandardInsertedEntity = this.issueStandardsMapper.find(id.getValue());
        Assert.assertNotNull(issueStandardInsertedEntity);
        this.issueStandardSupport.assertEqualsWithoutId(issueStandardUnidentifiedEntity, issueStandardInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final IssueStandardEntity issueStandardChangedEntity = this.issueStandardSupport.getEntityAdditionalMock(1);
        final IssueStandardEntity issueStandardBeforeUpdateEntity = this.issueStandardsMapper.find(issueStandardChangedEntity.getId());
        Assert.assertNotNull(issueStandardBeforeUpdateEntity);
        this.issueStandardSupport.assertNotEqualsWithoutId(issueStandardChangedEntity, issueStandardBeforeUpdateEntity);

        this.issueStandardsMapper.update(issueStandardChangedEntity);

        final IssueStandardEntity issueStandardUpdatedEntity = this.issueStandardsMapper.find(issueStandardChangedEntity.getId());
        this.issueStandardSupport.assertEquals(issueStandardChangedEntity, issueStandardUpdatedEntity);
    }

    @Test
    public void deleteTest() throws Exception {
        final Integer id = this.issueStandardSupport.getEntityFixtureMock(0).getId();
        final IssueStandardEntity issueStandardFoundedEntity = this.issueStandardsMapper.find(id);
        Assert.assertNotNull(issueStandardFoundedEntity);

        this.issueStandardsMapper.delete(issueStandardFoundedEntity);

        Assert.assertNull(this.issueStandardsMapper.find(id));
    }
}
