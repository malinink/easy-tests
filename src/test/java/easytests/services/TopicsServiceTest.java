package easytests.services;

import easytests.entities.Topic;
import easytests.mappers.TopicsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author loriens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsServiceTest {

    @Mock
    private TopicsMapper topicsMapper;

    @InjectMocks
    private TopicsService topicsService;

    @Test
    public void saveCreatesEntityTest() {
        final Topic topic = new Topic();
        this.topicsService.save(topic);
        verify(this.topicsMapper, times(1)).insert(topic);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final Topic topic = new Topic();
        topic.setId(1);
        this.topicsService.save(topic);
        verify(this.topicsMapper, times(1)).update(topic);
    }
}
