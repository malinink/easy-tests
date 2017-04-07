package easytests.services;

import easytests.entities.IssueEntity;
import easytests.mappers.IssuesMapper;
import easytests.models.IssueModel;
import easytests.models.IssueModelInterface;
import easytests.options.IssuesOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author fortyways
 */
@Service
public class IssuesService implements IssuesServiceInterface {

    @Autowired
    private IssuesMapper issuesMapper;

    @Autowired
    private QuizzesService quizzesService;

    @Override
    public List<IssueModelInterface> findAll() {
        return this.map(this.issuesMapper.findAll());
    }

    @Override
    public List<IssueModelInterface> findAll(IssuesOptionsInterface issueOptions) {
        return this.withServices(issueOptions).withRelations(this.findAll());
    }

    @Override
    public IssueModelInterface find(Integer id) {
        return this.map(this.issuesMapper.find(id));
    }

    @Override
    public IssueModelInterface find(Integer id, IssuesOptionsInterface issuesOptions) {
        return this.withServices(issuesOptions).withRelations(this.find(id));
    }

    @Override
    public void save(IssueModelInterface issueModel) {
        final IssueEntity issueEntity = this.map(issueModel);
        if (issueEntity.getId() == null) {
            this.issuesMapper.insert(issueEntity);
            issueModel.setId(issueEntity.getId());
            return;
        }
        this.issuesMapper.update(issueEntity);
    }

    @Override
    public void save(IssueModelInterface issueModel, IssuesOptionsInterface issuesOptions) {
        this.withServices(issuesOptions).saveWithRelations(issueModel);
    }

    @Override
    public void delete(IssueModelInterface issueModel) {
        final IssueEntity issueEntity = this.map(issueModel);
        if (issueEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.issuesMapper.delete(issueEntity);
    }

    @Override
    public void delete(IssueModelInterface issueModel, IssuesOptionsInterface issuesOptions) {
        this.withServices(issuesOptions).deleteWithRelations(issueModel);
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

    private IssuesOptionsInterface withServices(IssuesOptionsInterface issuesOptions) {
        issuesOptions.setIssuesService(this);
        issuesOptions.setQuizzesService(this.quizzesService);
        return issuesOptions;
    }

}
