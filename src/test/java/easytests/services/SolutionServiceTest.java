package easytests.services;

import easytests.entities.PointInterface;
import easytests.entities.Solution;
import easytests.entities.SolutionInterface;
import easytests.mappers.SolutionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.*;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionServiceTest {

    @Mock
    private SolutionMapper solutionMapper;

    @InjectMocks
    private SolutionService solutionService;

    @Test
    public void findAllTest() throws Exception {
        this.solutionService.findAll();
        verify(this.solutionMapper, times(1)).findAll();
    }

    @Test
    public void findTest() throws Exception {
        Integer id = anyInt();
        this.solutionService.find(id);
        verify(solutionMapper, times(1)).find(id);
    }

    @Test
    public void findByPointTest() throws Exception {
        PointInterface point = mock(PointInterface.class);
        this.solutionService.findByPoint(point);
        verify(solutionMapper, times(1)).findByPoint(point);
    }

    @Test
    public void saveUpdatesEntityTest() throws Exception {
        SolutionInterface solution = new Solution();
        solution.setId(5);
        this.solutionService.save(solution);
        verify(solutionMapper, times(1)).update(solution);
    }

    @Test
    public void saveInsertsEntityTest() throws Exception {
        SolutionInterface solution = new Solution();
        this.solutionService.save(solution);
        verify(solutionMapper, times(1)).insert(solution);
    }

    @Test
    public void deleteTest() throws Exception {
        SolutionInterface solution = new Solution();
        this.solutionService.delete(solution);
        verify(solutionMapper, times(1)).delete(solution);
    }
}
