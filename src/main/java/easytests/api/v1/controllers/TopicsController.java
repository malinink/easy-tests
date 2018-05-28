package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.mappers.TopicsMapper;
import easytests.api.v1.models.Topic;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.TopicsOptionsInterface;
import easytests.core.options.builder.TopicsOptionsBuilderInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author lelay
 */
@RestController("TopicsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/topics")
public class TopicsController extends AbstractController {

    @Autowired
    protected SubjectsServiceInterface subjectsService;

    @Autowired
    protected TopicsServiceInterface topicsService;

    @Autowired
    private TopicsOptionsBuilderInterface topicsOptionsBuilder;

    @Autowired
    @Qualifier("TopicsMapperV1")
    private TopicsMapper topicsMapper;

    @GetMapping("")
    public List<Topic> list(@RequestParam(name = "subjectId", required = true) Integer subjectId)
            throws NotFoundException, ForbiddenException {
        final SubjectModelInterface subjectModel = this.subjectsService.find(subjectId);

        if (subjectModel == null) {
            throw new NotFoundException();
        }

        if (!this.acl.hasAccess(subjectModel)) {
            throw new ForbiddenException();
        }

        final List<TopicModelInterface> topicsModels =
                this.topicsService.findBySubject(subjectModel);

        return topicsModels
                .stream()
                .map(model -> this.topicsMapper.map(model, Topic.class))
                .collect(Collectors.toList());
    }

    /**
     * create
     */
    /**
     * update
     */
    @GetMapping("/{topicId}")
    public Topic show(@PathVariable Integer topicId) throws NotFoundException, ForbiddenException {
        final TopicModelInterface topicModel = this.topicsService.find(topicId);

        if (topicModel == null) {
            throw new NotFoundException();
        }

        if (!this.acl.hasAccess(topicModel)) {
            throw new ForbiddenException();
        }
        return this.topicsMapper.map(topicModel, Topic.class);
    }

    @DeleteMapping("/{topicId}")
    public void delete(@PathVariable Integer topicId) throws NotFoundException, ForbiddenException {
        final TopicModelInterface topicModelforAuth = this.getTopicModel(topicId);

        if (!this.acl.hasAccess(topicModelforAuth)) {
            throw new ForbiddenException();
        }
        final TopicsOptionsInterface topicsOptionDelete = this.topicsOptionsBuilder.forDelete();
        final TopicModelInterface topicModelForDelete = this.topicsService.find(topicId, topicsOptionDelete);

        this.topicsService.delete(topicModelForDelete, topicsOptionDelete);
    }

    private TopicModelInterface getTopicModel(Integer id, TopicsOptionsInterface topicOptions)
            throws NotFoundException {
        final TopicModelInterface topicModel = this.topicsService.find(id, topicOptions);
        if (topicModel == null) {
            throw new NotFoundException();
        }
        return topicModel;
    }

    private TopicModelInterface getTopicModel(Integer id) throws NotFoundException {
        return this.getTopicModel(id, this.topicsOptionsBuilder.forAuth());
    }
}
