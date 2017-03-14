package easytests.services;

import easytests.entities.*;
import easytests.mappers.IssueStandardsMapper;
import easytests.models.IssueStandardModel;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SingularityA
 */
public class IssueStandardsService {

    @Autowired
    private IssueStandardsMapper issueStandardsMapper;

    public List<IssueStandardModelInterface> findAll() {
        return this.map(this.issueStandardsMapper.findAll());
    }

    public IssueStandardModelInterface find(Integer id) {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.find(id);
        if (issueStandardEntity == null) {
            return null;
        }
        return this.map(issueStandardEntity);
    }

    public IssueStandardModelInterface findBySubject(SubjectModelInterface subject) {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.findBySubjectId(subject.getId());
        if (issueStandardEntity == null) {
            return null;
        }
        return this.map(issueStandardEntity);
    }

    public void save(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = this.map(issueStandardModel);
        if (issueStandardEntity.getId() != null) {
            this.issueStandardsMapper.update(issueStandardEntity);
            return;
        }
        this.issueStandardsMapper.insert(issueStandardEntity);
        issueStandardModel.setId(issueStandardEntity.getId());
    }

    public void delete(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = this.map(issueStandardModel);
        if (issueStandardEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.issueStandardsMapper.delete(issueStandardEntity);
    }

    private IssueStandardModelInterface map(IssueStandardEntity issueStandardEntity) {
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.map(issueStandardEntity);
        return issueStandardModel;
    }

    private IssueStandardEntity map(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = new IssueStandardEntity();
        issueStandardEntity.map(issueStandardModel);
        return issueStandardEntity;
    }

    private List<IssueStandardModelInterface> map(List<IssueStandardEntity> issueStandardEntities) {
        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(issueStandardEntities.size());
        for (IssueStandardEntity issueStandardEntity: issueStandardEntities) {
            issueStandardModels.add(this.map(issueStandardEntity));
        }
        return issueStandardModels;
    }
}
