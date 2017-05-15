package easytests.models;

import easytests.entities.IssueEntity;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueModelTest {
    @Ignore
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(IssueModel.class);
        new EqualsMethodTester().testEqualsMethod(IssueModel.class);
        new HashCodeMethodTester().testHashCodeMethod(IssueModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer issueId = 1;
        final String name = "Name";
        final Integer subjectId = 1;
        final IssueEntity issueEntity = Mockito.mock(IssueEntity.class);

        Mockito.when(issueEntity.getId()).thenReturn(issueId);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getSubjectId()).thenReturn(subjectId);

        final IssueModel issueModel = new IssueModel();
        issueModel.map(issueEntity);

        Assert.assertEquals(issueId, issueModel.getId());
        Assert.assertEquals(name, issueModel.getName());
        Assert.assertEquals(new ModelsListEmpty(), issueModel.getQuizzes());
        Assert.assertEquals(new SubjectModelEmpty(subjectId), issueModel.getSubject());
    }

}
