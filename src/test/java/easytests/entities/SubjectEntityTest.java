package easytests.entities;

import easytests.entities.SubjectEntity;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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
        final Integer issueStandardId = 2;

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);

        final UserModelInterface user = Mockito.mock(UserModelInterface.class);
        Mockito.when(user.getId()).thenReturn(subjectUserId);

        final IssueStandardModelInterface issueStandard = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandard.getId()).thenReturn(issueStandardId);

        Mockito.when(subjectModel.getId()).thenReturn(subjectId);
        Mockito.when(subjectModel.getName()).thenReturn(subjectName);
        Mockito.when(subjectModel.getUser()).thenReturn(user);
        Mockito.when(subjectModel.getIssueStandard()).thenReturn(issueStandard);

        final SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.map(subjectModel);

        Assert.assertEquals(subjectId, subjectEntity.getId());
        Assert.assertEquals(subjectName, subjectEntity.getName());
        Assert.assertEquals(subjectUserId, subjectEntity.getUserId());
        Assert.assertEquals(issueStandardId, subjectEntity.getIssueStandardId());


    }

}