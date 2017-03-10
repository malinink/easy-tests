package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.SubjectEntity;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class SubjectsMapperTest {

    @Autowired
    private SubjectsMapper subjectsMapper;

    @Test
    public void testFind() throws Exception {

        final SubjectEntity subject = this.subjectsMapper.find(1);

        Assert.assertEquals((long) 1, (long) subject.getId());
        Assert.assertEquals("test1", subject.getName());

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

    }

    @Test
    public void testInsert() throws Exception {

        final Integer id = this.subjectsMapper.findAll().size() + 1;


        final Integer testUserId = 1;

        final String testName = "test";

        final SubjectEntity testSubject = Mockito.mock(SubjectEntity.class);

        Mockito.when(testSubject.getId()).thenReturn(id);
        Mockito.when(testSubject.getName()).thenReturn(testName);
        Mockito.when(testSubject.getUserId()).thenReturn(testUserId);

        subjectsMapper.insert(testSubject);

        verify(testSubject, times(1)).setId(id);

        final SubjectEntity readSubject = subjectsMapper.find(testSubject.getId());
        Assert.assertNotNull(readSubject);
        Assert.assertEquals(testUserId,readSubject.getUserId());
        Assert.assertEquals(testName,readSubject.getName());

    }

    @Test
    public void testUpdate() throws Exception {

        final Integer id = 2;
        final String name = "updated";

        SubjectEntity subject = this.subjectsMapper.find(id);

        Assert.assertNotEquals(name,subject.getName());

        subject = Mockito.mock(SubjectEntity.class);

        Mockito.when(subject.getId()).thenReturn(id);
        Mockito.when(subject.getName()).thenReturn(name);

        this.subjectsMapper.update(subject);

        final SubjectEntity readSubject = subjectsMapper.find(id);
        Assert.assertEquals(name, readSubject.getName());
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
