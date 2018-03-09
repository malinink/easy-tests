package easytests.core.entities;

import easytests.core.models.PointModelInterface;
import easytests.support.PointsSupport;
import org.junit.Test;


/**
 * @author SvetlanaTselikova
 */
public class PointEntityTest extends AbstractEntityTest {

    protected PointsSupport pointsSupport = new PointsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(PointEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        final PointEntity pointEntity = new PointEntity();

        pointEntity.map(pointModel);

        this.pointsSupport.assertEquals(pointModel, pointEntity);
    }
}
