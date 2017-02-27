package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.Subject;
import easytests.entities.SubjectInterface;
import easytests.entities.UserInterface;
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

import java.util.List;

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
        final SubjectInterface subject = this.subjectsMapper.find(1);

        Assert.assertEquals((long) 1, (long) subject.getId());
        Assert.assertEquals("test1", subject.getName());
    }

    @Test
    public void testFindAll() throws Exception {
        final List<SubjectInterface> subjects = this.subjectsMapper.findAll();

        Assert.assertNotNull(subjects);
        Assert.assertEquals((long) 3, (long) subjects.size());
    }


    @Test
    public void testUserNotNull() throws Exception {

        UserInterface user = Mockito.mock(UserInterface.class);
        Mockito.when(user.getId()).thenReturn(1);
        List<SubjectInterface> subjects = this.subjectsMapper.findByUser(user);

        Assert.assertNotNull(subjects);
        Assert.assertEquals(0,subjects.size());
    }


    @Test
    public void testFindByUser() throws Exception {

        UserInterface user = Mockito.mock(UserInterface.class);
        Mockito.when(user.getId()).thenReturn(3);

        List<SubjectInterface> subjects = this.subjectsMapper.findByUser(user);

        Assert.assertEquals(1,subjects.size());
        Assert.assertEquals("test3",subjects.get(0).getName());
    }

    @Test
    public void testInsert() throws Exception {
        final Subject testSubject = new Subject();

        testSubject.setName("test");
        testSubject.setUserId(1);

        subjectsMapper.insert(testSubject);

        final Subject readSubject = subjectsMapper.find(testSubject.getId());
        Assert.assertNotNull(readSubject);
    }

    @Test
    public void testUpdate() throws Exception {

        final SubjectInterface subject = this.subjectsMapper.find(2);
        subject.setName("updated");
        this.subjectsMapper.update(subject);

        final Subject readSubject = subjectsMapper.find(subject.getId());
        Assert.assertEquals("updated",readSubject.getName());
    }

    @Test
    public void testDelete() throws Exception {
        SubjectInterface subject = this.subjectsMapper.find(1);
        Assert.assertNotNull(subject);
        this.subjectsMapper.delete(subject);
        subject = this.subjectsMapper.find(1);
        Assert.assertNull(subject);
    }
}
