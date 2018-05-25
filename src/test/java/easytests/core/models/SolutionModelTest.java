package easytests.core.models;

import easytests.core.entities.SolutionEntity;
import easytests.support.SolutionsSupport;
import org.junit.Test;


/**
 * @author SvetlanaTselikova
 */
public class SolutionModelTest extends AbstractModelTest {

    private SolutionsSupport solutionsSupport = new SolutionsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(SolutionModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final SolutionEntity solutionEntity = this.solutionsSupport.getEntityFixtureMock(0);
        final SolutionModel solutionModel = new SolutionModel();

        solutionModel.map(solutionEntity);

        this.solutionsSupport.assertEquals(solutionEntity, solutionModel);
    }
}
