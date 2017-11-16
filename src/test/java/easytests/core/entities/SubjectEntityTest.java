package easytests.core.entities;

import easytests.core.models.SubjectModelInterface;
import easytests.support.SubjectsSupport;
import org.junit.Test;


/**
 * @author malinink
 */
public class SubjectEntityTest extends AbstractEntityTest {

    protected SubjectsSupport subjectsSupport = new SubjectsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(SubjectEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);
        final SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.map(subjectModel);

        this.subjectsSupport.assertEquals(subjectModel, subjectEntity);
    }

}
