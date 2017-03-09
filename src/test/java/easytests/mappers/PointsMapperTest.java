package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.*;
import easytests.mappers.PointsMapper;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * @author nikitalpopov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class PointsMapperTest {
    @Autowired
    private PointsMapper pointsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<Point> testPoints = this.pointsMapper.findAll();
        Assert.assertNotNull(testPoints);
        Assert.assertEquals(3, testPoints.size());
    }

    @Test
    public void testFind() throws Exception {
        final PointInterface testPoint = this.pointsMapper.find(1);
        Assert.assertNotNull(testPoint);
        Assert.assertEquals((Integer) 1, testPoint.getId());
        Assert.assertEquals("type1", testPoint.getType());
        Assert.assertEquals("text1", testPoint.getText());
        Assert.assertEquals((Integer) 1, testPoint.getQuizId());
    }

    @Test
    public void testInsert() throws Exception {
        final Point testPoint = new Point();
        testPoint.setType("type4");
        testPoint.setText("test4");
        testPoint.setQuizId(4);
        pointsMapper.insert(testPoint);

        final Point readPoint = pointsMapper.find(testPoint.getId());
        Assert.assertNotNull(readPoint);
    }

    @Test
    public void testUpdate() throws Exception {
        final PointInterface testPoint = this.pointsMapper.find(2);
        testPoint.setQuizId(4);
        this.pointsMapper.update(testPoint);

        final Point checkUpdatePoint = pointsMapper.find(testPoint.getId());
        Assert.assertEquals((Integer) 4, checkUpdatePoint.getQuizId());
    }

    @Test
    public void testDelete() throws Exception {
        PointInterface testPoint = this.pointsMapper.find(1);
        Assert.assertNotNull(testPoint);

        this.pointsMapper.delete(testPoint);
        testPoint = this.pointsMapper.find(1);
        Assert.assertNull(testPoint);
    }

}