package easytests.services;

import easytests.entities.Testee;
import easytests.entities.TesteeInterface;
import easytests.mappers.TesteesMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteesServiceTest {
    @MockBean
    private TesteesMapper testeesMapper;

    @Autowired
    private TesteesService testeesService;

    @Test
    public void findAllTest() throws Exception {
        this.testeesService.findAll();
        verify(this.testeesMapper, times(1)).findAll();
    }

    @Test
    public void findTest() throws Exception {
        Integer id = anyInt();
        this.testeesService.find(id);
        verify(testeesMapper, times(1)).find(id);
    }

    @Test
    public void saveCreatesEntityTest() {
        final Testee testee = new Testee();
        this.testeesService.save(testee);
        verify(this.testeesMapper, times(1)).insert(testee);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final Testee testee = new Testee();
        testee.setId(1);
        this.testeesService.save(testee);
        verify(this.testeesMapper, times(1)).update(testee);
    }

    @Test
    public void deleteTest() throws Exception {
        TesteeInterface testee = new Testee();
        this.testeesService.delete(testee);
        verify(testeesMapper, times(1)).delete(testee);
    }
}
