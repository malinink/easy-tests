package easytests.core.options;

import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssuesOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);

        issueModel.setId(1);
        given(issueModel.getSubject()).willReturn(new SubjectModelEmpty(1));

        final IssuesOptionsInterface issuesOptions = new IssuesOptions();

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        issuesOptions.setSubjectsService(subjectsService);
        issuesOptions.setQuizzesService(quizzesService);

        issuesOptions.withSubject(subjectsOptions);
        issuesOptions.withQuizzes(quizzesOptions);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(Mockito.mock(QuizModelInterface.class));

        given(subjectsService.find(issueModel.getSubject().getId(), subjectsOptions)).willReturn(subjectModel);
        given(quizzesService.findByIssue(issueModel, quizzesOptions)).willReturn(quizzesModels);

        final IssueModelInterface issueModelWithRelations = issuesOptions.withRelations(issueModel);

        Assert.assertEquals(issueModel, issueModelWithRelations);

        verify(subjectsService).find(1, subjectsOptions);
        verify(quizzesService).findByIssue(issueModel, quizzesOptions);
        verify(issueModel).setQuizzes(quizzesModels);
        verify(issueModel).setQuizzes(Mockito.anyList());
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {
        final IssueModelInterface issueModel = null;
        final IssuesOptionsInterface issuesOptions = new IssuesOptions();

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        issuesOptions.setSubjectsService(subjectsService);
        issuesOptions.setQuizzesService(quizzesService);
        issuesOptions.withSubject(subjectsOptions);
        issuesOptions.withQuizzes(quizzesOptions);

        final IssueModelInterface issueModelWithRelations = issuesOptions.withRelations(issueModel);

        Assert.assertEquals(null, issueModelWithRelations);
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {
        final IssueModelInterface issueModelFirst = Mockito.mock(IssueModelInterface.class);
        issueModelFirst.setId(1);
        given(issueModelFirst.getSubject()).willReturn(new SubjectModelEmpty(1));
        final IssueModelInterface issueModelSecond = Mockito.mock(IssueModelInterface.class);
        issueModelSecond.setId(2);
        given(issueModelSecond.getSubject()).willReturn(new SubjectModelEmpty(2));
        final List<IssueModelInterface> issuesModels = new ArrayList<>(2);
        issuesModels.add(issueModelFirst);
        issuesModels.add(issueModelSecond);

        final IssuesOptionsInterface issuesOptions = new IssuesOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        issuesOptions.setSubjectsService(subjectsService);
        issuesOptions.setQuizzesService(quizzesService);
        issuesOptions.withSubject(subjectsOptions);
        issuesOptions.withQuizzes(quizzesOptions);

        final List<QuizModelInterface> quizzesModelsFirst = new ArrayList<>();
        quizzesModelsFirst.add(Mockito.mock(QuizModelInterface.class));

        final List<QuizModelInterface> quizzesModelsSecond = new ArrayList<>();
        quizzesModelsSecond.add(Mockito.mock(QuizModelInterface.class));
        quizzesModelsSecond.add(Mockito.mock(QuizModelInterface.class));

        given(quizzesService.findByIssue(issueModelFirst, quizzesOptions)).willReturn(quizzesModelsFirst);
        given(quizzesService.findByIssue(issueModelSecond, quizzesOptions)).willReturn(quizzesModelsSecond);

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);

        given(subjectsService.find(1, subjectsOptions)).willReturn(subjectModelFirst);
        given(subjectsService.find(2, subjectsOptions)).willReturn(subjectModelSecond);

        final List<IssueModelInterface> issuesModelsWithRelations = issuesOptions.withRelations(issuesModels);

        Assert.assertEquals(issuesModelsWithRelations, issuesModels);
        verify(quizzesService).findByIssue(issueModelFirst, quizzesOptions);
        verify(subjectsService).find(1,subjectsOptions);
        verify(issuesModels.get(0)).setQuizzes(quizzesModelsFirst);
        verify(issuesModels.get(0)).setQuizzes(Mockito.anyList());
        verify(issuesModels.get(0)).setSubject(subjectModelFirst);

        verify(quizzesService).findByIssue(issueModelSecond, quizzesOptions);
        verify(subjectsService).find(2,subjectsOptions);
        verify(issuesModels.get(1)).setQuizzes(quizzesModelsSecond);
        verify(issuesModels.get(1)).setQuizzes(Mockito.anyList());
        verify(issuesModels.get(1)).setSubject(subjectModelSecond);
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);

        final IssuesOptionsInterface issuesOptions = new IssuesOptions();
        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        issuesOptions.setIssuesService(issuesService);
        issuesOptions.setQuizzesService(quizzesService);
        issuesOptions.withQuizzes(quizzesOptions);
        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(Mockito.mock(QuizModelInterface.class));
        given(issueModel.getQuizzes()).willReturn(quizzesModels);
        final InOrder inOrder = Mockito.inOrder(quizzesService, issuesService);

        issuesOptions.saveWithRelations(issueModel);

        inOrder.verify(issuesService).save(issueModel);
        inOrder.verify(quizzesService).save(quizzesModels, quizzesOptions);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        final IssuesOptionsInterface issuesOptions = new IssuesOptions();
        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);
        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        issuesOptions.setIssuesService(issuesService);
        issuesOptions.setQuizzesService(quizzesService);
        issuesOptions.withQuizzes(quizzesOptions);
        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(Mockito.mock(QuizModelInterface.class));
        given(issueModel.getQuizzes()).willReturn(quizzesModels);
        final InOrder inOrder = Mockito.inOrder(quizzesService, issuesService);

        issuesOptions.deleteWithRelations(issueModel);

        inOrder.verify(quizzesService).delete(quizzesModels, quizzesOptions);
        inOrder.verify(issuesService).delete(issueModel);
    }

    @Test
    public void testSaveDeleteWithSubject(){
        final IssuesOptionsInterface issuesOptions = new IssuesOptions();


        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);

        issuesOptions.setIssuesService(issuesService);
        issuesOptions.setSubjectsService(subjectsService);
        issuesOptions.withSubject(subjectsOptions);

        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);

        final IssuesOptionsInterface issuesOptionsSpy = Mockito.spy(issuesOptions);

        issuesOptionsSpy.saveWithRelations(issueModel);

        verify(subjectsService, times(1)).save(issueModel.getSubject(), subjectsOptions);

        issuesOptionsSpy.deleteWithRelations(issueModel);

        verify(subjectsService, times(1)).delete(issueModel.getSubject(), subjectsOptions);

    }
}
