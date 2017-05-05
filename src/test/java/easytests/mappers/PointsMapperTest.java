package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.*;
import easytests.mappers.PointsMapper;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

        final List<PointEntity> testPointEntities = this.pointsMapper.findAll();

        Assert.assertNotNull(testPointEntities);
        Assert.assertEquals((long) 3, (long) testPointEntities.size());

    }

    @Test
    public void testFind() throws Exception {

        final PointEntity testPoint = this.pointsMapper.find(1);

        Assert.assertNotNull(testPoint);
        Assert.assertEquals((Integer) 1, testPoint.getId());
        Assert.assertEquals("type1", testPoint.getType());
        Assert.assertEquals("text1", testPoint.getText());
        Assert.assertEquals((Integer) 1, testPoint.getQuizId());
    }

    @Test
    public void testInsert() throws Exception {

        final Integer testId = this.pointsMapper.findAll().size() + 1;
        final String testType = "Test type";
        final String testText = "Test text";
        final Integer testQuizId = 1;

        final PointEntity testPoint = Mockito.mock(PointEntity.class);

        Mockito.when(testPoint.getId()).thenReturn(testId);
        Mockito.when(testPoint.getType()).thenReturn(testType);
        Mockito.when(testPoint.getText()).thenReturn(testText);
        Mockito.when(testPoint.getQuizId()).thenReturn(testQuizId);

        pointsMapper.insert(testPoint);

        final PointEntity readPoint = pointsMapper.find(testPoint.getId());

        Assert.assertNotNull(readPoint);
        Assert.assertEquals(testId, readPoint.getId());
        Assert.assertEquals(testType, readPoint.getType());
        Assert.assertEquals(testText, readPoint.getText());
        Assert.assertEquals(testQuizId, readPoint.getQuizId());

    }

    @Test
    public void testUpdate() throws Exception {

        final Integer testId = 2;
        final String testType = "Test type";
        final String testText = "Test text";
        final Integer testQuizId = 1;

        PointEntity testPoint = this.pointsMapper.find(testId);

        Assert.assertNotEquals(testType, testPoint.getType());
        Assert.assertNotEquals(testText, testPoint.getType());
        Assert.assertNotEquals(testQuizId, testPoint.getQuizId());

        testPoint = Mockito.mock(PointEntity.class);

        Mockito.when(testPoint.getId()).thenReturn(testId);
        Mockito.when(testPoint.getType()).thenReturn(testType);
        Mockito.when(testPoint.getText()).thenReturn(testText);
        Mockito.when(testPoint.getQuizId()).thenReturn(testQuizId);

        this.pointsMapper.update(testPoint);

        final PointEntity checkUpdatePoint = pointsMapper.find(testId);

        Assert.assertEquals(testType, checkUpdatePoint.getType());
        Assert.assertEquals(testText, checkUpdatePoint.getText());
        Assert.assertEquals(testQuizId, checkUpdatePoint.getQuizId());

    }

    @Test
    public void testDelete() throws Exception {

        PointEntity testPoint = this.pointsMapper.find(1);
        Assert.assertNotNull(testPoint);

        this.pointsMapper.delete(testPoint);

        testPoint = this.pointsMapper.find(1);
        Assert.assertNull(testPoint);

    }

}
