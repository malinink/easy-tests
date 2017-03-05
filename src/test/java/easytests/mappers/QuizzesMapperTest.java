package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.*;
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
public class QuizzesMapperTest {

    @Autowired
    private QuizzesMapper quizzesMapper;

    @Test
    public void testFind() throws Exception {

        final QuizInterface quiz = this.quizzesMapper.find(1);

        Assert.assertEquals((Integer) 1, quiz.getId());
        Assert.assertEquals((Integer)1, quiz.getIssueId());
        Assert.assertEquals("test_invite_code1", quiz.getInviteCode());

    }


    @Test
    public void testFindAll() throws Exception {

        final List<QuizInterface> quizzes = this.quizzesMapper.findAll();

        Assert.assertNotNull(quizzes);
        Assert.assertEquals((long) 3, (long) quizzes.size());

    }


    @Test
    public void testIssueNotNull() throws Exception {

        IssueInterface issue = Mockito.mock(IssueInterface.class);
        Mockito.when(issue.getId()).thenReturn(5);
        List<QuizInterface> quizzes = this.quizzesMapper.findByIssue(issue);

        Assert.assertNotNull(quizzes);
        Assert.assertEquals(0,quizzes.size());

    }


    @Test
    public void testFindByIssue() throws Exception {

        IssueInterface issue = Mockito.mock(IssueInterface.class);
        Mockito.when(issue.getId()).thenReturn(3);
        List<QuizInterface> quizzes = this.quizzesMapper.findByIssue(issue);

        Assert.assertEquals(1,quizzes.size());
        Assert.assertEquals((Integer)3,quizzes.get(0).getIssueId());

    }

    @Test
    public void testInsert() throws Exception {

        final Quiz testQuiz = new Quiz();
        testQuiz.setIssueId(1);
        quizzesMapper.insert(testQuiz);

        final Quiz readQuiz = quizzesMapper.find(testQuiz.getId());
        Assert.assertNotNull(readQuiz);

    }

    @Test
    public void testUpdate() throws Exception {

        final QuizInterface subject = this.quizzesMapper.find(2);
        subject.setIssueId(3);
        this.quizzesMapper.update(subject);

        final Quiz readSubject = quizzesMapper.find(subject.getId());
        Assert.assertEquals((Integer)3,readSubject.getIssueId());

    }

    @Test
    public void testDelete() throws Exception {

        QuizInterface quiz = this.quizzesMapper.find(1);
        Assert.assertNotNull(quiz);

        this.quizzesMapper.delete(quiz);

        quiz = this.quizzesMapper.find(1);
        Assert.assertNull(quiz);

    }
}
