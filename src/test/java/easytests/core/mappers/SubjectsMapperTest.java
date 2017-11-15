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
        final List<SubjectEntity> subjectsEntities = this.subjectsMapper.findAll();

        Assert.assertEquals(3, subjectsEntities.size());

        Integer index = 0;
        for (SubjectEntity subbjectEntity: subjectsEntities) {
            final SubjectEntity subjectFixtureEntity = this.subjectsSupport.getEntityFixtureMock(index);

            this.subjectsSupport.assertEquals(subjectFixtureEntity, subbjectEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final SubjectEntity subjectFixtureEntity = this.subjectsSupport.getEntityFixtureMock(0);
        final SubjectEntity subjectEntity = this.subjectsMapper.find(1);

        this.subjectsSupport.assertEquals(subjectFixtureEntity, subjectEntity);
    }


    @Test
    public void testFindByUserIdWithoutSubjects() throws Exception {
        final List<SubjectEntity> subjectsEntities = this.subjectsMapper.findByUserId(1);

        Assert.assertEquals(0, subjectsEntities.size());
    }

    @Test
    public void testFindByUserId() throws Exception {
        final List<SubjectEntity> subjectsFixturesEntity = new ArrayList<>();
        subjectsFixturesEntity.add(this.subjectsSupport.getEntityFixtureMock(0));
        subjectsFixturesEntity.add(this.subjectsSupport.getEntityFixtureMock(1));
        final List<SubjectEntity> subjectsEntities = this.subjectsMapper.findByUserId(2);

        Assert.assertEquals(2, subjectsEntities.size());
        this.subjectsSupport.assertEquals(subjectsFixturesEntity.get(0), subjectsEntities.get(0));
        this.subjectsSupport.assertEquals(subjectsFixturesEntity.get(1), subjectsEntities.get(1));
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final SubjectEntity subjectAdditionalEntity = this.subjectsSupport.getEntityAdditionalMock(0);
        this.subjectsMapper.insert(subjectAdditionalEntity);

        verify(subjectAdditionalEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final SubjectEntity subjectInsertedEntity = this.subjectsMapper.find(id.getValue());

        Assert.assertNotNull(subjectInsertedEntity);
        this.subjectsSupport.assertEqualsWithoutId(subjectAdditionalEntity, subjectInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final SubjectEntity subjectAdditionalEntity = this.subjectsSupport.getEntityAdditionalMock(1);
        final Integer id = subjectAdditionalEntity.getId();
        final SubjectEntity subjectEntity = this.subjectsMapper.find(id);

        Assert.assertNotNull(subjectEntity);
        this.subjectsSupport.assertNotEqualsWithoutId(subjectAdditionalEntity, subjectEntity);

        this.subjectsMapper.update(subjectAdditionalEntity);
        final SubjectEntity subjectUpdatedEntity = this.subjectsMapper.find(id);

        this.subjectsSupport.assertEquals(subjectAdditionalEntity, subjectUpdatedEntity);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.subjectsSupport.getEntityFixtureMock(0).getId();
        SubjectEntity subjectEntity = this.subjectsMapper.find(id);

        Assert.assertNotNull(subjectEntity);

        this.subjectsMapper.delete(subjectEntity);

        subjectEntity = this.subjectsMapper.find(id);
        Assert.assertNull(subjectEntity);
    }

}
