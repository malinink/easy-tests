package easytests.core.models;

import easytests.core.entities.SubjectEntity;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.UserModelEmpty;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author vkpankov
 */
public class SubjectModelTest {

    @Ignore
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(SubjectModel.class);
        new EqualsMethodTester().testEqualsMethod(SubjectModel.class);
        new HashCodeMethodTester().testHashCodeMethod(SubjectModel.class);

    }

    @Test
    public void testMap() throws Exception {

        final Integer subjectId = 5;
        final String subjectName = "Test subject";
        final String subjectDescription = "Test subject description";
        final Integer subjectUserId = 3;

        final SubjectEntity subjectEntity = Mockito.mock(SubjectEntity.class);
        Mockito.when(subjectEntity.getId()).thenReturn(subjectId);
        Mockito.when(subjectEntity.getName()).thenReturn(subjectName);
        Mockito.when(subjectEntity.getDescription()).thenReturn(subjectDescription);
        Mockito.when(subjectEntity.getUserId()).thenReturn(subjectUserId);

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.map(subjectEntity);

        Assert.assertEquals(subjectId, subjectModel.getId());
        Assert.assertEquals(subjectName, subjectModel.getName());
        Assert.assertEquals(subjectDescription, subjectModel.getDescription());
        Assert.assertEquals(new ModelsListEmpty(), subjectModel.getTopics());
        Assert.assertEquals(new UserModelEmpty(subjectUserId), subjectModel.getUser());
        Assert.assertEquals(new IssueStandardModelEmpty(), subjectModel.getIssueStandard());
        Assert.assertEquals(new ModelsListEmpty(), subjectModel.getIssues());
    }

}
