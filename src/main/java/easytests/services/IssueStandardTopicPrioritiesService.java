package easytests.services;

import easytests.entities.IssueStandardTopicPriorityEntity;
import easytests.mappers.IssueStandardTopicPrioritiesMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardTopicPriorityModel;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardTopicPrioritiesService {

    @Autowired
    private IssueStandardTopicPrioritiesMapper topicPrioritiesMapper;

    public List<IssueStandardTopicPriorityModelInterface> findAll() {
        return this.map(this.topicPrioritiesMapper.findAll());
    }

    public IssueStandardTopicPriorityModelInterface find(Integer id) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.topicPrioritiesMapper.find(id);
        if (topicPriorityEntity == null) {
            return null;
        }
        return this.map(topicPriorityEntity);
    }

    public List<IssueStandardTopicPriorityModelInterface>
        findByIssueStandard(IssueStandardModelInterface issueStandard) {
        return this.map(this.topicPrioritiesMapper.findByIssueStandardId(issueStandard.getId()));
    }

    public void save(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.map(topicPriorityModel);
        if (topicPriorityEntity.getId() != null) {
            this.topicPrioritiesMapper.update(topicPriorityEntity);
            return;
        }
        this.topicPrioritiesMapper.insert(topicPriorityEntity);
        topicPriorityModel.setId(topicPriorityEntity.getId());
    }

    public void delete(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.map(topicPriorityModel);
        if (topicPriorityEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.topicPrioritiesMapper.delete(topicPriorityEntity);
    }

    private IssueStandardTopicPriorityModelInterface map(IssueStandardTopicPriorityEntity topicPriorityEntity) {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel = new IssueStandardTopicPriorityModel();
        topicPriorityModel.map(topicPriorityEntity);
        return topicPriorityModel;
    }

    private IssueStandardTopicPriorityEntity map(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = new IssueStandardTopicPriorityEntity();
        topicPriorityEntity.map(topicPriorityModel);
        return topicPriorityEntity;
    }

    private List<IssueStandardTopicPriorityModelInterface>
        map(List<IssueStandardTopicPriorityEntity> topicPriorityEntities) {

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels
                = new ArrayList<>(topicPriorityEntities.size());

        for (IssueStandardTopicPriorityEntity topicPriorityEntity : topicPriorityEntities) {
            topicPriorityModels.add(this.map(topicPriorityEntity));
        }
        return topicPriorityModels;
    }
}
