package easytests.support;

import com.sun.org.apache.bcel.internal.generic.ISUB;
import easytests.core.entities.IssueStandardEntity;
import easytests.core.models.IssueStandardModel;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.options.IssueStandardsOptions;
import easytests.core.options.IssueStandardsOptionsInterface;
import easytests.core.services.IssueStandardsService;
import easytests.core.services.SubjectsService;
import easytests.core.services.SubjectsServiceInterface;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author janchk
 */

public class IssueStandardSupport {

    private static SubjectModelEmpty sub1 = getEmptySubject(1);
    private static SubjectModelEmpty sub2 = getEmptySubject(2);
    private static SubjectModelEmpty sub3 = getEmptySubject(3);
    private static SubjectModelEmpty sub4 = getEmptySubject(4);
    private static SubjectModelEmpty sub5 = getEmptySubject(5);

    //    SubjectModelEmpty subjectModelEmpty;
    private static Object[][] fixtures = new Object[][]{
            {
                    1,
                    600,
                    10,
                    sub1,
            },
            {
                    2,
                    1200,
                    20,
                    sub2,
            },
            {
                    5,
                    3600,
                    30,
                    sub3,
            },
            {
                    3,
                    600,
                    10,
                    sub4
            }
    };

    private static Object[][] additional = new Object[][]{
            {
                    null,
                    1200,
                    20,
                    sub5
            },
            {
                    null,
                    600,
                    10,
                    sub1
            }
    };


    public IssueStandardEntity getEntityFixtureMock(Integer index){
        return this.getEntityMock(fixtures[index]);
    }
    public IssueStandardEntity getEntityAdditionalMock(Integer index){
        return this.getEntityMock(additional[index]);
    }

    static SubjectModelEmpty getEmptySubject(Integer index){
        final SubjectModelEmpty mock = Mockito.mock(SubjectModelEmpty.class);
        mock.setId(index);
        return mock;
    }

    private IssueStandardEntity getEntityMock(Object[] data){
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2],
                (SubjectModelEmpty) data[3]
        );
    }

    private IssueStandardEntity getEntityMock(
            Integer id,
            Integer timeLimit,
            Integer questiuonsNumber,
            SubjectModelEmpty subject
    ) {
        final IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Integer subjectId = subject.getId();
        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questiuonsNumber);
        Mockito.when(issueStandardEntity.getSubject()).thenReturn(subject);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);
        return issueStandardEntity;
    }

    public IssueStandardModelInterface getModelFixtureMock(Integer index){
        return this.getModelMock(fixtures[index]);
    }
    public IssueStandardModelInterface getModelAdditionalMock(Integer index){
        return this.getModelMock(additional[index]);
    }

    private IssueStandardModelInterface getModelMock(Object[] data){
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2],
                (SubjectModelEmpty) data[3]
        );
    }

    private IssueStandardModelInterface getModelMock(
            Integer id,
            Integer timeLimit,
            Integer questiuonsNumber,
            SubjectModelEmpty subject
    ) {
        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Integer subjectId = subject.getId();
        Mockito.when(issueStandardModel.getId()).thenReturn(id);
        Mockito.when(issueStandardModel.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardModel.getQuestionsNumber()).thenReturn(questiuonsNumber);
        Mockito.when(issueStandardModel.getSubject()).thenReturn(subject);
//        Mockito.when(issueStandardModel.getSubject().getId()).thenReturn(subjectId);
        return issueStandardModel;
    }

    public void assertModelsListEquals(List<IssueStandardModelInterface> expected,
                                       List<IssueStandardModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i =0;
        for (IssueStandardModelInterface issueStandardModel: expected){
            this.assertEquals(issueStandardModel, actual.get(i));
            i++;
        }

    }

    public void assertEquals(IssueStandardModelInterface expected,
                             IssueStandardModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestionsNumber(), actual.getQuestionsNumber());
        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
//        Assert.assertEquals(expected.getSubject().getId(), actual.getSubject().getId());
    }

    public void assertEquals(IssueStandardEntity expected, IssueStandardEntity actual){
//        Assert.assertEquals(expected.getId(), actual.getId());
//        Assert.assertEquals(expected.getQuestionNumber(), actual.getQuestionNumber());
//        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
//        Assert.assertEquals(expected.getId(), actual.getSubject().getId());
    }
}
