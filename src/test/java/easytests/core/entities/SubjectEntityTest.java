package easytests.core.entities;

import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.UserModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * @author vkpankov
 */
public class SubjectEntityTest {


    @Test
    public void testCommon() throws Exception {

        new BeanTester().testBean(SubjectEntity.class);
        new EqualsMethodTester().testEqualsMethod(SubjectEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(SubjectEntity.class);

    }

    @Test
    public  void testMap() throws Exception {

        final Integer subjectId = 5;
        final String subjectName = "Test subject";
        final Integer subjectUserId = 1;

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);

        Mockito.when(subjectModel.getId()).thenReturn(subjectId);
        Mockito.when(subjectModel.getName()).thenReturn(subjectName);
        Mockito.when(subjectModel.getTopics()).thenReturn(new ModelsListEmpty());
        Mockito.when(subjectModel.getUser()).thenReturn(new UserModelEmpty(subjectUserId));
        Mockito.when(subjectModel.getIssueStandard()).thenReturn(new IssueStandardModelEmpty());
        Mockito.when(subjectModel.getIssues()).thenReturn(new ModelsListEmpty());
        final SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.map(subjectModel);

        Assert.assertEquals(subjectId, subjectEntity.getId());
        Assert.assertEquals(subjectName, subjectEntity.getName());
        Assert.assertEquals(subjectUserId, subjectEntity.getUserId());

    }

}