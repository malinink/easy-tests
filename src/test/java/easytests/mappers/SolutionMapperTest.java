package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.AnswerInterface;
import easytests.entities.PointInterface;
import easytests.entities.Solution;
import easytests.entities.SolutionInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class SolutionMapperTest {

    @Autowired
    private SolutionMapper solutionMapper;

    @Test
    public void findAllTest() throws Exception {
        List<Solution> solutions = this.solutionMapper.findAll();
        Assert.assertNotNull(solutions);
        Assert.assertEquals(5, solutions.size());
    }

    @Test
    public void findTest() throws Exception {
        SolutionInterface solution = this.solutionMapper.find(1);
        Assert.assertNotNull(solution);
        Assert.assertEquals((Integer) 1, solution.getId());
        Assert.assertNull(solution.getAnswer());
        Assert.assertNull(solution.getPoint());
    }

    @Test
    public void findByPointTest() throws Exception {
        PointInterface point = Mockito.mock(PointInterface.class);
        Mockito.when(point.getId()).thenReturn(1);

        List<Solution> solutions = this.solutionMapper.findByPoint(point);
        Assert.assertNotNull(solutions);
        Assert.assertEquals(2, solutions.size());
    }

    @Test
    public void insertTest() throws Exception {
        SolutionInterface solution = new Solution();

        AnswerInterface answer = Mockito.mock(AnswerInterface.class);
        Mockito.when(answer.getId()).thenReturn(22);

        PointInterface point = Mockito.mock(PointInterface.class);
        Mockito.when(point.getId()).thenReturn(3);

        solution.setAnswer(answer).setPoint(point);
        this.solutionMapper.insert(solution);

        solution = this.solutionMapper.find(6);
        Assert.assertNotNull(solution);
        Assert.assertEquals((Integer) 6, solution.getId());
    }

    @Test
    public void updateTest() throws Exception {
        SolutionInterface solution = this.solutionMapper.find(1);

        AnswerInterface answer = Mockito.mock(AnswerInterface.class);
        Mockito.when(answer.getId()).thenReturn(50);

        PointInterface point = Mockito.mock(PointInterface.class);
        Mockito.when(point.getId()).thenReturn(4);

        solution.setAnswer(answer).setPoint(point);
        this.solutionMapper.update(solution);

        List<Solution> solutions = this.solutionMapper.findByPoint(point);
        Assert.assertNotNull(solutions);
        Assert.assertEquals(1, solutions.size());
    }

    @Test
    public void deleteTest() throws Exception {
        SolutionInterface solution = this.solutionMapper.find(5);
        Assert.assertNotNull(solution);
        this.solutionMapper.delete(solution);
        solution = this.solutionMapper.find(5);
        Assert.assertNull(solution);
    }
}
