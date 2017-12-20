package easytests.core.mappers;

import easytests.core.entities.SubjectEntity;
import easytests.support.SubjectsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
public class SubjectsMapperTest extends AbstractMapperTest {

    protected SubjectsSupport subjectsSupport = new SubjectsSupport();

    @Autowired
    private SubjectsMapper subjectsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<SubjectEntity> subjectsFoundedEntities = this.subjectsMapper.findAll();

        Assert.assertEquals(3, subjectsFoundedEntities.size());

        Integer index = 0;
        for (SubjectEntity subbjectEntity: subjectsFoundedEntities) {
            final SubjectEntity subjectFixtureEntity = this.subjectsSupport.getEntityFixtureMock(index);

            this.subjectsSupport.assertEquals(subjectFixtureEntity, subbjectEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final SubjectEntity subjectFixtureEntity = this.subjectsSupport.getEntityFixtureMock(0);

        final SubjectEntity subjectFoundedEntity = this.subjectsMapper.find(1);

        this.subjectsSupport.assertEquals(subjectFixtureEntity, subjectFoundedEntity);
    }

    @Test
    public void testFindByUserId() throws Exception {
        final List<SubjectEntity> subjectsFixtureEntities = new ArrayList<>();
        subjectsFixtureEntities.add(this.subjectsSupport.getEntityFixtureMock(0));
        subjectsFixtureEntities.add(this.subjectsSupport.getEntityFixtureMock(1));

        final List<SubjectEntity> subjectsFoundedEntities = this.subjectsMapper.findByUserId(2);

        Assert.assertEquals(2, subjectsFoundedEntities.size());

        Integer index = 0;
        for (SubjectEntity subjectEntity: subjectsFoundedEntities) {
            this.subjectsSupport.assertEquals(subjectsFixtureEntities.get(index), subjectEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final SubjectEntity subjectUnidentifiedEntity = this.subjectsSupport.getEntityAdditionalMock(0);

        this.subjectsMapper.insert(subjectUnidentifiedEntity);

        verify(subjectUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final SubjectEntity subjectInsertedEntity = this.subjectsMapper.find(id.getValue());

        Assert.assertNotNull(subjectInsertedEntity);
        this.subjectsSupport.assertEqualsWithoutId(subjectUnidentifiedEntity, subjectInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final SubjectEntity subjectChangedEntity = this.subjectsSupport.getEntityAdditionalMock(1);
        final SubjectEntity subjectBeforeUpdateEntity = this.subjectsMapper.find(subjectChangedEntity.getId());

        Assert.assertNotNull(subjectBeforeUpdateEntity);
        this.subjectsSupport.assertNotEqualsWithoutId(subjectChangedEntity, subjectBeforeUpdateEntity);

        this.subjectsMapper.update(subjectChangedEntity);
        final SubjectEntity subjectUpdatedEntity = this.subjectsMapper.find(subjectChangedEntity.getId());

        this.subjectsSupport.assertEquals(subjectChangedEntity, subjectUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.subjectsSupport.getEntityFixtureMock(0).getId();
        final SubjectEntity subjectFoundedEntity = this.subjectsMapper.find(id);

        Assert.assertNotNull(subjectFoundedEntity);

        this.subjectsMapper.delete(subjectFoundedEntity);

        Assert.assertNull(this.subjectsMapper.find(id));
    }

}
