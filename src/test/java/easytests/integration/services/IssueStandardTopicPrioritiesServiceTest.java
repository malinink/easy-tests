package easytests.integration.services;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.options.IssueStandardTopicPrioritiesOptions;
import easytests.core.options.IssueStandardsOptions;
import easytests.core.services.IssueStandardTopicPrioritiesService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssueStandardTopicPrioritiesServiceTest {

    @Autowired
    private IssueStandardTopicPrioritiesService topicPrioritiesService;

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(id, 1, true, 1);

        final IssueStandardTopicPriorityModelInterface foundedIopicPriorityModel = this.topicPrioritiesService.find(id);
        Assert.assertEquals(topicPriorityModel, foundedIopicPriorityModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final IssueStandardTopicPriorityModelInterface foundedIopicPriorityModel = this.topicPrioritiesService.find(id);

        Assert.assertNull(foundedIopicPriorityModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer issueStandardId = 1;

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(id, 1, true, issueStandardId);
        final IssueStandardModelInterface issueStandardModel
                = Models.createIssueStandardModel(issueStandardId, 300, 30, 1);
        topicPriorityModel.setIssueStandard(issueStandardModel);

        final IssueStandardTopicPriorityModelInterface foundedTopicPriorityModel
                = this.topicPrioritiesService
                .find(id, new IssueStandardTopicPrioritiesOptions().withIssueStandard(new IssueStandardsOptions()));

        Assert.assertEquals(topicPriorityModel, foundedTopicPriorityModel);
        Assert.assertEquals(issueStandardModel, foundedTopicPriorityModel.getIssueStandard());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final Integer id = this.topicPrioritiesService.findAll().size() + 1;
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(null, 4, true, 4);

        this.topicPrioritiesService.save(topicPriorityModel);
        final IssueStandardTopicPriorityModelInterface foundedTopicPriorityModel
                = this.topicPrioritiesService.find(id);

        Assert.assertEquals(topicPriorityModel, foundedTopicPriorityModel);
    }

    @Test
    public void testSaveUpdatesModel() throws Exception {
        final Integer id = 1;
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(id, 1, false, 1);
        Assert.assertNotEquals(topicPriorityModel, this.topicPrioritiesService.find(id));

        this.topicPrioritiesService.save(topicPriorityModel);

        Assert.assertEquals(topicPriorityModel, this.topicPrioritiesService.find(id));
    }
}
