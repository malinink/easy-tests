package easytests.services;

import easytests.entities.Point;
import easytests.entities.PointInterface;
import easytests.mappers.PointsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;


/**
 * @author nikitalpopov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PointsServiceTest {
    @Mock
    private PointsMapper pointsMapper;

    @InjectMocks
    private PointsService pointsService;

    @Test
    public void testFindAll() throws Exception {
        this.pointsService.findAll();
        verify(this.pointsMapper, times(1)).findAll();
    }

    @Test
    public void testFind() throws Exception {
        Integer id = anyInt();
        this.pointsService.find(id);
        verify(this.pointsMapper, times(1)).find(id);
    }

    @Test
    public void testSave() throws Exception {
        PointInterface testPoint = new Point();
        this.pointsService.save(testPoint);
        verify(this.pointsMapper, times(1)).insert(testPoint);
    }

    @Test
    public void testAnotherSave() throws Exception {
        PointInterface testPoint = new Point();
        testPoint.setId(3);
        this.pointsService.save(testPoint);
        verify(this.pointsMapper, times(1)).update(testPoint);
    }

    @Test
    public void delete() throws Exception {
        PointInterface testPoint = new Point();
        this.pointsService.delete(testPoint);
        verify(this.pointsMapper, times(1)).delete(testPoint);
    }

}