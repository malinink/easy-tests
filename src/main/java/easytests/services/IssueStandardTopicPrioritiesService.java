package easytests.services;

import easytests.entities.IssueStandardTopicPriorityEntity;
import easytests.mappers.IssueStandardTopicPrioritiesMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardTopicPriorityModel;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.options.IssueStandardTopicPrioritiesOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class IssueStandardTopicPrioritiesService implements IssueStandardTopicPrioritiesServiceInterface {

    @Autowired
    private IssueStandardTopicPrioritiesMapper topicPrioritiesMapper;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Override
    public List<IssueStandardTopicPriorityModelInterface> findAll() {
        return this.map(this.topicPrioritiesMapper.findAll());
    }

    @Override
    public List<IssueStandardTopicPriorityModelInterface> findAll(
            IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        return this.withServices(topicPrioritiesOptions).withRelations(this.findAll());
    }

    @Override
    public IssueStandardTopicPriorityModelInterface find(Integer id) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.topicPrioritiesMapper.find(id);
        if (topicPriorityEntity == null) {
            return null;
        }
        return this.map(topicPriorityEntity);
    }

    @Override
    public IssueStandardTopicPriorityModelInterface find(
            Integer id, IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        return this.withServices(topicPrioritiesOptions).withRelations(this.find(id));
    }

    @Override
    public List<IssueStandardTopicPriorityModelInterface> findByIssueStandard(
            IssueStandardModelInterface issueStandard) {

        return this.map(this.topicPrioritiesMapper.findByIssueStandardId(issueStandard.getId()));
    }

    @Override
    public List<IssueStandardTopicPriorityModelInterface> findByIssueStandard(
            IssueStandardModelInterface issueStandard,
            IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        return this.withServices(topicPrioritiesOptions).withRelations(this.findByIssueStandard(issueStandard));
    }

    @Override
    public void save(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.map(topicPriorityModel);
        if (topicPriorityEntity.getId() != null) {
            this.topicPrioritiesMapper.update(topicPriorityEntity);
            return;
        }
        this.topicPrioritiesMapper.insert(topicPriorityEntity);
        topicPriorityModel.setId(topicPriorityEntity.getId());
    }

    @Override
    public void save(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels) {
        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            this.save(topicPriorityModel);
        }
    }

    @Override
    public void save(IssueStandardTopicPriorityModelInterface topicPriorityModel,
                     IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        this.withServices(topicPrioritiesOptions).saveWithRelations(topicPriorityModel);
    }

    @Override
    public void save(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels,
                     IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            this.save(topicPriorityModel, topicPrioritiesOptions);
        }
    }

    @Override
    public void delete(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.map(topicPriorityModel);
        if (topicPriorityEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.topicPrioritiesMapper.delete(topicPriorityEntity);
    }

    @Override
    public void delete(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels) {
        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            this.delete(topicPriorityModel);
        }
    }

    @Override
    public void delete(IssueStandardTopicPriorityModelInterface topicPriorityModel,
                       IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        this.withServices(topicPrioritiesOptions).deleteWithRelations(topicPriorityModel);
    }

    @Override
    public void delete(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels,
                       IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            this.delete(topicPriorityModel, topicPrioritiesOptions);
        }
    }

    private IssueStandardTopicPrioritiesOptionsInterface
        withServices(IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {

        topicPrioritiesOptions.setTopicPrioritiesService(this);
        topicPrioritiesOptions.setTopicsService(this.topicsService);
        topicPrioritiesOptions.setIssueStandardsService(this.issueStandardsService);
        return topicPrioritiesOptions;
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
