package easytests.core.models;

import easytests.core.entities.PointEntity;
import easytests.support.PointsSupport;
import org.junit.Test;


/**
 * @author AnyaMaz
 */
public class PointModelTest extends AbstractModelTest {

    private PointsSupport pointsSupport = new PointsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(PointModel.class);

    }

    @Test
    public void testMap() throws Exception {
        final PointEntity pointEntity = this.pointsSupport.getEntityFixtureMock(0);
        final PointModel pointModel = new PointModel();

        pointModel.map(pointEntity);

        this.pointsSupport.assertEquals(pointEntity, pointModel);
    }
}



