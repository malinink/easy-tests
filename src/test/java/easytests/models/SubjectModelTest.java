package easytests.models;

import easytests.entities.SubjectEntity;
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

        final SubjectEntity subjectEntity = Mockito.mock(SubjectEntity.class);
        Mockito.when(subjectEntity.getId()).thenReturn(subjectId);
        Mockito.when(subjectEntity.getName()).thenReturn(subjectName);

        final SubjectModel subjectModel = new SubjectModel();
        subjectModel.map(subjectEntity);

        Assert.assertEquals(subjectId, subjectModel.getId());
        Assert.assertEquals(subjectName, subjectModel.getName());

    }

}
