package easytests.services;

import easytests.models.SubjectModelInterface;
import easytests.models.TopicModelInterface;
import easytests.options.TopicsOptionsInterface;

import java.util.List;

/**
 * @author vkpankov
 */
public interface TopicsServiceInterface extends ServiceInterface {

    List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel, TopicsOptionsInterface topicsOptions);

    List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel);

}
