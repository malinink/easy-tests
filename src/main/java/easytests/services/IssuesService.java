package easytests.services;

import easytests.entities.IssueEntity;
import easytests.mappers.IssuesMapper;
import easytests.models.IssueModel;
import easytests.models.IssueModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
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

    public List<IssueModelInterface> findAll() {
        return this.map(this.issuesMapper.findAll());
    }

    public IssueModelInterface find(Integer id) {
        final IssueEntity issueEntity = issuesMapper.find(id);
        if (issueEntity == null) {
            return null;
        }
        return this.map(issueEntity);
    }

    public void save(IssueModelInterface issueModel) {
        final IssueEntity issueEntity = this.map(issueModel);
        if (issueEntity.getId() == null) {
            this.issuesMapper.insert(issueEntity);
            return;
        }
        this.issuesMapper.update(issueEntity);
    }

    public void delete(IssueModelInterface issueModel) {
        final IssueEntity issueEntity = this.map(issueModel);
        if (issueEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.issuesMapper.delete(issueEntity);
    }

    private IssueModelInterface map(IssueEntity issueEntity) {
        final IssueModelInterface issueModel = new IssueModel();
        issueModel.map(issueEntity);
        return issueModel;
    }

    private IssueEntity map(IssueModelInterface issueModel) {
        final IssueEntity issueEntity = new IssueEntity();
        issueEntity.map(issueModel);
        return issueEntity;
    }

    private List<IssueModelInterface> map(List<IssueEntity> issuesList) {
        final List<IssueModelInterface> resultIssueList = new ArrayList(issuesList.size());
        for (IssueEntity issue: issuesList) {
            resultIssueList.add(this.map(issue));
        }
        return resultIssueList;
    }
}
