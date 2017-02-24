package easytests.services;

import easytests.entities.Topic;
import easytests.entities.User;
import easytests.mappers.TopicsMapper;
import easytests.mappers.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author loriens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsServiceTest {

    @MockBean
    private TopicsMapper topicsMapper;

    @Autowired
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
