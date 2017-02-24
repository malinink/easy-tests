package easytests.services;

import easytests.entities.Issue;
import easytests.entities.IssueInterface;
import easytests.mappers.IssuesMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author fortyways
 */
@Service
public class IssuesService {
    @Autowired
    private IssuesMapper issuesMapper;

    public List<IssueInterface> findAll() {
        return this.map(this.issuesMapper.findAll());
    }

    public IssueInterface find(Integer id) {
        return this.issuesMapper.find(id);
    }

    public void save(IssueInterface issue) {
        if (issue.getId() == null) {
            this.issuesMapper.insert(issue);
            return;
        }
        this.issuesMapper.update(issue);
    }

    public void delete(IssueInterface issue) {
        this.issuesMapper.delete(issue);
    }

    private List<IssueInterface> map(List<Issue> issuesList) {
        final List<IssueInterface> resultIssueList = new ArrayList<>(issuesList.size());
        for (Issue issue: issuesList) {
            resultIssueList.add(issue);
        }
        return resultIssueList;
    }
}
