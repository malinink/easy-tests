package easytests.options;

import easytests.models.*;
import easytests.models.empty.UserModelEmpty;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.TopicsServiceInterface;
import easytests.services.UsersServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;
import org.mockito.InOrder;

import static org.mockito.Mockito.times;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectsOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);

        subjectModel.setId(1);
        given(subjectModel.getUser()).willReturn(new UserModelEmpty(1));

        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();

        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardService = Mockito.mock(IssueStandardsServiceInterface.class);
        final UsersServiceInterface usersService = Mockito.mock(UsersServiceInterface.class);

        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        final IssueStandardsOptionsInterface issueStandardOptions = Mockito.mock(IssueStandardsOptionsInterface.class);
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);

        subjectsOptions.setTopicsService(topicsService);
        subjectsOptions.setIssueStandardsService(issueStandardService);
        subjectsOptions.setUsersService(usersService);

        subjectsOptions.withTopics(topicsOptions);
        subjectsOptions.withIssueStandard(issueStandardOptions);
        subjectsOptions.withUser(usersOptions);

        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        topicsModels.add(Mockito.mock(TopicModelInterface.class));

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);

        given(topicsService.findBySubject(subjectModel, topicsOptions)).willReturn(topicsModels);
        given(issueStandardService.findBySubject(subjectModel, issueStandardOptions)).willReturn(issueStandardModel);
        given(usersService.find(subjectModel.getUser().getId(), usersOptions)).willReturn(userModel);

        final SubjectModelInterface subjectModelWithRelations = subjectsOptions.withRelations(subjectModel);

        Assert.assertEquals(subjectModel, subjectModelWithRelations);

        verify(topicsService).findBySubject(subjectModel, topicsOptions);
        verify(issueStandardService).findBySubject(subjectModel, issueStandardOptions);
        verify(usersService).find(1, usersOptions);

        verify(subjectModel).setTopics(topicsModels);
        verify(subjectModel).setIssueStandard(issueStandardModel);
        verify(subjectModel).setUser(userModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();

        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final UsersServiceInterface usersService = Mockito.mock(UsersServiceInterface.class);

        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        final IssueStandardsOptionsInterface issueStandardOptions = Mockito.mock(IssueStandardsOptionsInterface.class);
        final UsersOptionsInterface userOptions = Mockito.mock(UsersOptionsInterface.class);

        subjectsOptions.setTopicsService(topicsService);
        subjectsOptions.setIssueStandardsService(issueStandardsService);
        subjectsOptions.setUsersService(usersService);

        subjectsOptions.withTopics(topicsOptions).withIssueStandard(issueStandardOptions).withUser(userOptions);

        final SubjectModelInterface nullSubjectModel = null;
        final SubjectModelInterface subjectModelWithRelations = subjectsOptions.withRelations(nullSubjectModel);

        Assert.assertNull(subjectModelWithRelations);

    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        subjectModelFirst.setId(1);
        given(subjectModelFirst.getUser()).willReturn(new UserModelEmpty(1));
        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);
        subjectModelSecond.setId(2);
        given(subjectModelSecond.getUser()).willReturn(new UserModelEmpty(2));
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>(2);
        subjectsModels.add(subjectModelFirst);
        subjectsModels.add(subjectModelSecond);

        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();

        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);

        final IssueStandardsServiceInterface issueStandardService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final UsersServiceInterface usersService = Mockito.mock(UsersServiceInterface.class);
        final UsersOptionsInterface userOptions = Mockito.mock(UsersOptionsInterface.class);

        subjectsOptions.setTopicsService(topicsService);
        subjectsOptions.setIssueStandardsService(issueStandardService);
        subjectsOptions.setUsersService(usersService);

        subjectsOptions.withTopics(topicsOptions);
        subjectsOptions.withIssueStandard(issueStandardOptions);
        subjectsOptions.withUser(userOptions);

        final List<TopicModelInterface> topicsModelsFirst = new ArrayList<>();
        topicsModelsFirst.add(Mockito.mock(TopicModelInterface.class));

        final List<TopicModelInterface> topicsModelsSecond = new ArrayList<>();
        topicsModelsSecond.add(Mockito.mock(TopicModelInterface.class));
        topicsModelsSecond.add(Mockito.mock(TopicModelInterface.class));

        given(topicsService.findBySubject(subjectModelFirst, topicsOptions)).willReturn(topicsModelsFirst);
        given(topicsService.findBySubject(subjectModelSecond, topicsOptions)).willReturn(topicsModelsSecond);

        final IssueStandardModelInterface issueStandardModelFirst = Mockito.mock(IssueStandardModelInterface.class);
        final IssueStandardModelInterface issueStandardModelSecond = Mockito.mock(IssueStandardModelInterface.class);
        given(issueStandardService.findBySubject(subjectModelFirst, issueStandardOptions))
                .willReturn(issueStandardModelFirst);
        given(issueStandardService.findBySubject(subjectModelSecond, issueStandardOptions))
                .willReturn(issueStandardModelSecond);

        final UserModelInterface userModelFirst = Mockito.mock(UserModelInterface.class);
        final UserModelInterface userModelSecond = Mockito.mock(UserModelInterface.class);
        given(usersService.find(1, userOptions)).willReturn(userModelFirst);
        given(usersService.find(2, userOptions)).willReturn(userModelSecond);

        final List<SubjectModelInterface> subjectsModelsWithRelations = subjectsOptions.withRelations(subjectsModels);

        Assert.assertEquals(subjectsModelsWithRelations, subjectsModels);
        verify(topicsService).findBySubject(subjectModelFirst, topicsOptions);
        verify(issueStandardService).findBySubject(subjectModelFirst, issueStandardOptions);
        verify(usersService).find(1, userOptions);

        verify(subjectsModels.get(0)).setTopics(topicsModelsFirst);
        verify(subjectsModels.get(0)).setTopics(Mockito.anyList());
        verify(subjectsModels.get(0)).setIssueStandard(issueStandardModelFirst);
        verify(subjectsModels.get(0)).setUser(userModelFirst);

        verify(topicsService).findBySubject(subjectModelSecond, topicsOptions);
        verify(issueStandardService).findBySubject(subjectModelSecond, issueStandardOptions);
        verify(usersService).find(2, userOptions);

        verify(subjectsModels.get(1)).setTopics(topicsModelsSecond);
        verify(subjectsModels.get(1)).setTopics(Mockito.anyList());
        verify(subjectsModels.get(1)).setIssueStandard(issueStandardModelSecond);
        verify(subjectsModels.get(1)).setUser(userModelSecond);
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

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);

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

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);

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

        final SubjectsOptionsInterface subjectsOptionsSpy = Mockito.spy(subjectsOptions);

        subjectsOptionsSpy.deleteWithRelations(subjectModel);

        verify(usersOptions, times(1)).deleteWithRelations(subjectModel.getUser());

        subjectsOptionsSpy.saveWithRelations(subjectModel);

        verify(usersOptions, times(1)).saveWithRelations(subjectModel.getUser());

    }
}
