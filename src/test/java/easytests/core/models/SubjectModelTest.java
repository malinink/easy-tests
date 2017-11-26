package easytests.core.models;

import easytests.core.entities.SubjectEntity;
import easytests.support.SubjectsSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;


/**
 * @author malinink
 */
public class SubjectModelTest extends AbstractModelTest {

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder()
                .ignoreProperty("user")
                .ignoreProperty("issueStandard");
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(SubjectModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final SubjectEntity subjectEntity = this.subjectsSupport.getEntityFixtureMock(0);
        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.map(subjectEntity);

        this.subjectsSupport.assertEquals(subjectEntity, subjectModel);
    }

}
