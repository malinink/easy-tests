package easytests.core.entities;

import easytests.core.models.SolutionModelInterface;
import easytests.support.SolutionsSupport;
import org.junit.Test;


/**
 * @author SvetlanaTselikova
 */
public class SolutionEntityTest extends AbstractEntityTest {

    private SolutionsSupport solutionsSupport = new SolutionsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(SolutionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelFixtureMock(0);
        final SolutionEntity solutionEntity = new SolutionEntity();

        solutionEntity.map(solutionModel);

        this.solutionsSupport.assertEquals(solutionModel, solutionEntity);
    }

}
