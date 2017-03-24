package easytests.options;

import easytests.models.*;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.TopicsServiceInterface;
import easytests.services.UsersServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectsOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final SubjectsOptionsInterface subjectOptions = new SubjectsOptions();

        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardService = Mockito.mock(IssueStandardsServiceInterface.class);


        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        final IssueStandardsOptionsInterface issueStandardOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        subjectOptions.setTopicsService(topicsService);
        subjectOptions.setIssueStandardsService(issueStandardService);

        subjectOptions.withTopics(topicsOptions);
        subjectOptions.withIssueStandard(issueStandardOptions);


        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        given(topicsService.findBySubject(Mockito.any(SubjectModelInterface.class))).willReturn(topicsModels);

        IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);

        given(topicsService.findBySubject(Mockito.any(SubjectModelInterface.class))).willReturn(topicsModels);
        //given(issueStandardService.findBySubject(Mockito.any(SubjectModelInterface.class), issueStandardOptions)).willReturn(issueStandardModel);

        final SubjectModelInterface subjectModelWithRelations = subjectOptions.withRelations(subjectModel);

        Assert.assertEquals(subjectModel, subjectModelWithRelations);
        verify(topicsService).findBySubject(subjectModel, topicsOptions);
        verify(issueStandardService).findBySubject(subjectModel, issueStandardOptions);

        Assert.assertEquals(topicsModels, subjectModelWithRelations.getTopics());
        //Assert.assertEquals(issueStandardModel, subjectModelWithRelations.getIssueStandard());

        //TODO: vkpankov
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjecModelSecond = Mockito.mock(SubjectModelInterface.class);

        final List<SubjectModelInterface> subjectsModels = new ArrayList<>(2);
        subjectsModels.add(subjectModelFirst);
        subjectsModels.add(subjecModelSecond);

        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();
        SubjectsOptionsInterface subjectsOptionsSpy = Mockito.spy(subjectsOptions);

        subjectsOptionsSpy.withRelations(subjectsModels);

        Mockito.verify(subjectsOptionsSpy, times(1)).withRelations(subjectModelFirst);
        Mockito.verify(subjectsOptionsSpy, times(1)).withRelations(subjecModelSecond);

    }


    @Test
    public void testSaveWithRelations() throws Exception {
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();

        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);


        subjectsOptions.setSubjectsService(subjectsService);
        subjectsOptions.setTopicsService(topicsService);
        subjectsOptions.setIssueStandardsService(issueStandardsService);
        subjectsOptions.withTopics(topicsOptions);
        subjectsOptions.withIssueStandard(issueStandardsOptions);

        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        subjectModel.setTopics(topicsModels);

        IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);

        subjectModel.setIssueStandard(issueStandardModel);

        final InOrder inOrder = Mockito.inOrder(subjectsService, issueStandardsService, topicsService);

        subjectsOptions.saveWithRelations(subjectModel);

        inOrder.verify(subjectsService).save(subjectModel);
        inOrder.verify(issueStandardsService).save(subjectModel.getIssueStandard(), issueStandardsOptions);
        inOrder.verify(topicsService).save(subjectModel.getTopics(), topicsOptions);

    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();

        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);


        subjectsOptions.setSubjectsService(subjectsService);
        subjectsOptions.setTopicsService(topicsService);
        subjectsOptions.setIssueStandardsService(issueStandardsService);
        subjectsOptions.withTopics(topicsOptions);
        subjectsOptions.withIssueStandard(issueStandardsOptions);

        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        subjectModel.setTopics(topicsModels);

        IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);

        subjectModel.setIssueStandard(issueStandardModel);

        final InOrder inOrder = Mockito.inOrder(topicsService, issueStandardsService, subjectsService);

        subjectsOptions.deleteWithRelations(subjectModel);

        inOrder.verify(topicsService).delete(subjectModel.getTopics(), topicsOptions);
        inOrder.verify(issueStandardsService).delete(subjectModel.getIssueStandard(), issueStandardsOptions);
        inOrder.verify(subjectsService).delete(subjectModel);

    }

    @Test
    public void testSaveDeleteWithUser() {

        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();

        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);

        final UsersServiceInterface usersService = Mockito.mock(UsersServiceInterface.class);
        subjectsOptions.setUsersService(usersService);
        subjectsOptions.withUser(usersOptions);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);

        SubjectsOptionsInterface subjectsOptionsSpy = Mockito.spy(subjectsOptions);

        subjectsOptionsSpy.deleteWithRelations(subjectModel);

        verify(usersOptions, times(1)).deleteWithRelations(subjectModel.getUser());

        subjectsOptionsSpy.saveWithRelations(subjectModel);

        verify(usersOptions, times(1)).saveWithRelations(subjectModel.getUser());

    }
}
