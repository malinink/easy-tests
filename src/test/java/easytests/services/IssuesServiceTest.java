package easytests.services;

import easytests.entities.Issue;
import easytests.mappers.IssuesMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssuesServiceTest {
    @MockBean
    private IssuesMapper issuesMapper;

    @Autowired
    private IssuesService issuesService;

    @Test
    public void saveCreatesEntityTest() {
        final Issue issue = new Issue();
        this.issuesService.save(issue);
        verify(this.issuesMapper, times(1)).insert(issue);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final Issue issue = new Issue();
        issue.setId(1);
        this.issuesService.save(issue);
        verify(this.issuesMapper, times(1)).update(issue);
    }
    @Test
    public void testDelete(){
        final Issue issue = new Issue();
        this.issuesService.delete(issue);
        verify(this.issuesMapper, times(1)).delete(issue);
    }
    @Test
    public void testFind(){
        this.issuesService.find(1);
        verify(this.issuesMapper, times(1)).find(1);
    }
    @Test
    public void testFindAll(){
        this.issuesService.findAll();
        verify(this.issuesMapper, times(1)).findAll();
    }
}
