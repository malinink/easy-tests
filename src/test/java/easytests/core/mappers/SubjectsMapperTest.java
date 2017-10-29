package easytests.core.mappers;

import easytests.config.DatabaseConfig;
import easytests.core.entities.SubjectEntity;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Transactional
public class SubjectsMapperTest {

    @Autowired
    private SubjectsMapper subjectsMapper;

    @Test
    public void testFind() throws Exception {

        final SubjectEntity subject = this.subjectsMapper.find(1);

        Assert.assertEquals((long) 1, (long) subject.getId());
        Assert.assertEquals("test1", subject.getName());
        Assert.assertEquals("testdescription1", subject.getDescription());

    }

    @Test
    public void testFindAll() throws Exception {

        final List<SubjectEntity> subjectEntities = this.subjectsMapper.findAll();

        Assert.assertNotNull(subjectEntities);
        Assert.assertEquals((long) 3, (long) subjectEntities.size());

    }

    @Test
    public void testUserNotNull() throws Exception {

        final List<SubjectEntity> subjectEntities = this.subjectsMapper.findByUserId(1);

        Assert.assertNotNull(subjectEntities);
        Assert.assertEquals(0, subjectEntities.size());

    }

    @Test
    public void testFindByUserId() throws Exception {

        final List<SubjectEntity> subjectEntities = this.subjectsMapper.findByUserId(3);

        Assert.assertEquals(1, subjectEntities.size());
        Assert.assertEquals("test3", subjectEntities.get(0).getName());
        Assert.assertEquals("testdescription3", subjectEntities.get(0).getDescription());

    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final Integer testUserId = 1;
        final String testName = "test";
        final String testDescription = "testSubject.description";

        final SubjectEntity testSubject = Mockito.mock(SubjectEntity.class);

        Mockito.when(testSubject.getName()).thenReturn(testName);
        Mockito.when(testSubject.getDescription()).thenReturn(testDescription);
        Mockito.when(testSubject.getUserId()).thenReturn(testUserId);

        subjectsMapper.insert(testSubject);

        verify(testSubject, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        final SubjectEntity readSubject = subjectsMapper.find(id.getValue());
        Assert.assertNotNull(readSubject);
        Assert.assertEquals(id.getValue(), readSubject.getId());
        Assert.assertEquals(testUserId, readSubject.getUserId());
        Assert.assertEquals(testName, readSubject.getName());
        Assert.assertEquals(testDescription, readSubject.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {

        final Integer id = 2;
        final String name = "updated";
        final String description = "updated description";

        SubjectEntity subject = this.subjectsMapper.find(id);

        Assert.assertNotEquals(name, subject.getName());
        Assert.assertNotEquals(description, subject.getDescription());

        subject = Mockito.mock(SubjectEntity.class);

        Mockito.when(subject.getId()).thenReturn(id);
        Mockito.when(subject.getName()).thenReturn(name);
        Mockito.when(subject.getDescription()).thenReturn(description);

        this.subjectsMapper.update(subject);

        final SubjectEntity readSubject = subjectsMapper.find(id);
        Assert.assertEquals(name, readSubject.getName());
        Assert.assertEquals(description, readSubject.getDescription());

    }

    @Test
    public void testDelete() throws Exception {
        SubjectEntity subject = this.subjectsMapper.find(1);
        Assert.assertNotNull(subject);
        this.subjectsMapper.delete(subject);
        subject = this.subjectsMapper.find(1);
        Assert.assertNull(subject);
    }
}
