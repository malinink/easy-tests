package easytests.core.entities;

import easytests.core.models.IssueModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author fortyways
 */
public class IssueEntityTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(UserEntity.class);
        new EqualsMethodTester().testEqualsMethod(IssueEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(IssueEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer issueId = 420;
        final String name = "Name";
        final Integer subjectId = 13;


        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        Mockito.when(issueModel.getId()).thenReturn(issueId);
        Mockito.when(issueModel.getName()).thenReturn(name);
        Mockito.when(issueModel.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));

        final IssueEntity issueEntity = new IssueEntity();
        issueEntity.map(issueModel);

        Assert.assertEquals(issueId, issueEntity.getId());
        Assert.assertEquals(name, issueEntity.getName());
        Assert.assertEquals(subjectId, issueEntity.getSubjectId());
    }
}
