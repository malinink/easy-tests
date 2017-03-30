package easytests.options;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardTopicPriorityModel;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardTopicPrioritiesOptionsTest {

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = new IssueStandardTopicPrioritiesOptions();

        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        topicPrioritiesOptions.setIssueStandardsService(issueStandardsService);
        topicPrioritiesOptions.withIssueStandard(issueStandardsOptions);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel = null;

        final IssueStandardTopicPriorityModelInterface topicPriorityModelWithRelations
                = topicPrioritiesOptions.withRelations(topicPriorityModel);

        Assert.assertEquals(null, topicPriorityModelWithRelations);
    }

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = new IssueStandardTopicPrioritiesOptions();

        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardId = 10;
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = new IssueStandardTopicPriorityModel();
        topicPriorityModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);
        given(issueStandardsService.find(issueStandardId, issueStandardsOptions)).willReturn(issueStandardModel);

        // options не заданы
        final IssueStandardTopicPriorityModelInterface topicPriorityModelWithoutRelations
                = topicPrioritiesOptions.withRelations(topicPriorityModel);

        verify(issueStandardsService, times(0)).find(issueStandardId, issueStandardsOptions);
        Assert.assertEquals(topicPriorityModel, topicPriorityModelWithoutRelations);
        Assert.assertNotEquals(issueStandardModel, topicPriorityModelWithoutRelations.getIssueStandard());

        topicPrioritiesOptions.setIssueStandardsService(issueStandardsService);
        topicPrioritiesOptions.withIssueStandard(issueStandardsOptions);

        // теперь options заданы
        final IssueStandardTopicPriorityModelInterface topicPriorityModelWithRelations
                = topicPrioritiesOptions.withRelations(topicPriorityModel);

        verify(issueStandardsService, times(1)).find(issueStandardId, issueStandardsOptions);
        Assert.assertEquals(topicPriorityModel, topicPriorityModelWithRelations);
        Assert.assertEquals(issueStandardModel, topicPriorityModelWithRelations.getIssueStandard());
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = new IssueStandardTopicPrioritiesOptions();

        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardIdFirst = 10;
        final Integer issueStandardIdSecond = 20;
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>(2);

        IssueStandardTopicPriorityModelInterface topicPriorityModelFirst
                = new IssueStandardTopicPriorityModel();
        IssueStandardTopicPriorityModelInterface topicPriorityModelSecond
                = new IssueStandardTopicPriorityModel();

        topicPriorityModelFirst.setIssueStandard(new IssueStandardModelEmpty(issueStandardIdFirst));
        topicPriorityModelSecond.setIssueStandard(new IssueStandardModelEmpty(issueStandardIdSecond));
        topicPriorityModels.add(topicPriorityModelFirst);
        topicPriorityModels.add(topicPriorityModelSecond);

        final IssueStandardModelInterface issueStandardModelFirst = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModelFirst.getId()).thenReturn(issueStandardIdFirst);
        given(issueStandardsService.find(issueStandardIdFirst, issueStandardsOptions))
                .willReturn(issueStandardModelFirst);

        final IssueStandardModelInterface issueStandardModelSecond = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModelSecond.getId()).thenReturn(issueStandardIdSecond);
        given(issueStandardsService.find(issueStandardIdSecond, issueStandardsOptions))
                .willReturn(issueStandardModelSecond);

        // options не заданы
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelsWithoutRelations
                = topicPrioritiesOptions.withRelations(topicPriorityModels);

        verify(issueStandardsService, times(0)).find(issueStandardIdFirst, issueStandardsOptions);
        verify(issueStandardsService, times(0)).find(issueStandardIdSecond, issueStandardsOptions);
        Assert.assertEquals(topicPriorityModels, topicPriorityModelsWithoutRelations);
        Assert.assertNotEquals(issueStandardModelFirst, topicPriorityModelsWithoutRelations.get(0).getIssueStandard());
        Assert.assertNotEquals(issueStandardModelSecond, topicPriorityModelsWithoutRelations.get(1).getIssueStandard());
        Assert.assertNotEquals(issueStandardModelFirst, topicPriorityModelsWithoutRelations.get(0).getIssueStandard());
        Assert.assertNotEquals(issueStandardModelSecond, topicPriorityModelsWithoutRelations.get(1).getIssueStandard());

        topicPrioritiesOptions.setIssueStandardsService(issueStandardsService);
        topicPrioritiesOptions.withIssueStandard(issueStandardsOptions);

        // теперь options заданы
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelsWithRelations
                = topicPrioritiesOptions.withRelations(topicPriorityModels);

        verify(issueStandardsService, times(1)).find(issueStandardIdFirst, issueStandardsOptions);
        verify(issueStandardsService, times(1)).find(issueStandardIdSecond, issueStandardsOptions);
        Assert.assertEquals(topicPriorityModels, topicPriorityModelsWithRelations);
        Assert.assertEquals(issueStandardModelFirst, topicPriorityModelsWithRelations.get(0).getIssueStandard());
        Assert.assertEquals(issueStandardModelSecond, topicPriorityModelsWithRelations.get(1).getIssueStandard());
        Assert.assertEquals(issueStandardModelFirst, topicPriorityModelsWithRelations.get(0).getIssueStandard());
        Assert.assertEquals(issueStandardModelSecond, topicPriorityModelsWithRelations.get(1).getIssueStandard());
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = new IssueStandardTopicPrioritiesOptions();
        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardId = 10;
        final IssueStandardModelInterface issueStandardModel
                = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = new IssueStandardTopicPriorityModel();
        topicPriorityModel.setIssueStandard(issueStandardModel);

        topicPrioritiesOptions.setTopicPrioritiesService(topicPrioritiesService);
        topicPrioritiesOptions.setIssueStandardsService(issueStandardsService);
        topicPrioritiesOptions.withIssueStandard(issueStandardsOptions);

        final InOrder inOrder = Mockito.inOrder(issueStandardsService, topicPrioritiesService);

        topicPrioritiesOptions.saveWithRelations(topicPriorityModel);

        inOrder.verify(issueStandardsService).save(topicPriorityModel.getIssueStandard(), issueStandardsOptions);
        inOrder.verify(topicPrioritiesService).save(topicPriorityModel);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = new IssueStandardTopicPrioritiesOptions();
        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardId = 10;
        final IssueStandardModelInterface issueStandardModel
                = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = new IssueStandardTopicPriorityModel();
        topicPriorityModel.setIssueStandard(issueStandardModel);

        topicPrioritiesOptions.setTopicPrioritiesService(topicPrioritiesService);
        topicPrioritiesOptions.setIssueStandardsService(issueStandardsService);
        topicPrioritiesOptions.withIssueStandard(issueStandardsOptions);

        final InOrder inOrder = Mockito.inOrder(issueStandardsService, topicPrioritiesService);

        topicPrioritiesOptions.deleteWithRelations(topicPriorityModel);

        inOrder.verify(topicPrioritiesService).delete(topicPriorityModel);
        inOrder.verify(issueStandardsService).delete(topicPriorityModel.getIssueStandard(), issueStandardsOptions);
    }
}
