package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.IssueStandardInterface;
import easytests.entities.IssueStandardQuestionTypeOption;
import easytests.entities.IssueStandardQuestionTypeOptionInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssueStandardQuestionTypeOptionMapperTest {

    @Autowired
    private IssueStandardQuestionTypeOptionMapper questionTypeOptionMapper;

    @Test
    public void findAllTest() throws Exception {
        List<IssueStandardQuestionTypeOption> questionTypeOptions = this.questionTypeOptionMapper.findAll();

        Assert.assertNotNull(questionTypeOptions);
        Assert.assertEquals(5, questionTypeOptions.size());
    }

    @Test
    public void findTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface questionTypeOption = this.questionTypeOptionMapper.find(1);

        Assert.assertEquals((Integer) 1, questionTypeOption.getId());
        Assert.assertEquals((Integer) 1, questionTypeOption.getQuestionTypeId());
        Assert.assertEquals((Integer) 1, questionTypeOption.getMinQuestions());
        Assert.assertEquals(null, questionTypeOption.getMaxQuestions());
        Assert.assertEquals(null, questionTypeOption.getTimeLimit());
        Assert.assertEquals((Integer) 1, questionTypeOption.getIssueStandardId());
    }

    @Test
    public void findByIssueStandardTest() throws Exception {
        IssueStandardInterface issueStandard = Mockito.mock(IssueStandardInterface.class);
        Mockito.when(issueStandard.getId()).thenReturn(1);

        final List<IssueStandardQuestionTypeOption> questionTypeOptions
                = this.questionTypeOptionMapper.findByIssueStandard(issueStandard);

        Assert.assertEquals(3, questionTypeOptions.size());

        IssueStandardQuestionTypeOptionInterface questionTypeOption = questionTypeOptions.get(2);
        Assert.assertEquals((Integer) 3, questionTypeOption.getId());
        Assert.assertEquals((Integer) 3, questionTypeOption.getQuestionTypeId());
        Assert.assertEquals((Integer) 5, questionTypeOption.getMinQuestions());
        Assert.assertEquals((Integer) 10, questionTypeOption.getMaxQuestions());
        Assert.assertEquals(null, questionTypeOption.getTimeLimit());
        Assert.assertEquals((Integer) 1, questionTypeOption.getIssueStandardId());
    }

    @Test
    public void insertTest() throws Exception {
        IssueStandardQuestionTypeOptionInterface questionTypeOption = new IssueStandardQuestionTypeOption();
        questionTypeOption.setQuestionTypeId(2).setMinQuestions(5).setMaxQuestions(10).setIssueStandardId(2);
        this.questionTypeOptionMapper.insert(questionTypeOption);

        questionTypeOption = this.questionTypeOptionMapper.find(6);
        Assert.assertNotNull(questionTypeOption);
        Assert.assertEquals((Integer) 2, questionTypeOption.getQuestionTypeId());
        Assert.assertEquals((Integer) 5, questionTypeOption.getMinQuestions());
        Assert.assertEquals((Integer) 10, questionTypeOption.getMaxQuestions());
        Assert.assertEquals((Integer) 2, questionTypeOption.getIssueStandardId());
    }

    @Test
    public void updateTest() throws Exception {
        IssueStandardQuestionTypeOptionInterface questionTypeOption = this.questionTypeOptionMapper.find(5);
        Assert.assertNotNull(questionTypeOption);
        questionTypeOption.setMinQuestions(1).setMaxQuestions(2).setTimeLimit(600);
        this.questionTypeOptionMapper.update(questionTypeOption);
        questionTypeOption = this.questionTypeOptionMapper.find(5);
        Assert.assertEquals((Integer) 1, questionTypeOption.getMinQuestions());
        Assert.assertEquals((Integer) 2, questionTypeOption.getMaxQuestions());
        Assert.assertEquals((Integer) 600, questionTypeOption.getTimeLimit());
    }

    @Test
    public void deleteTest() throws Exception {
        IssueStandardQuestionTypeOptionInterface questionTypeOption = this.questionTypeOptionMapper.find(1);
        Assert.assertNotNull(questionTypeOption);
        this.questionTypeOptionMapper.delete(questionTypeOption);
        questionTypeOption = this.questionTypeOptionMapper.find(1);
        Assert.assertNull(questionTypeOption);
    }
}
