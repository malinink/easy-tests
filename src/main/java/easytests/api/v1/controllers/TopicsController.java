package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.mappers.TopicsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Topic;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.builder.SubjectsOptionsBuilder;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author lelay
 */
@RestController("TopicsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/topics")
public class TopicsController extends AbstractController {

    @Autowired
    protected SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Autowired
    protected TopicsServiceInterface topicsService;

    @Autowired
    protected SubjectsServiceInterface subjectsService;

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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody Topic topic) throws Exception {
        if (topic.getId() != null) {
            throw new IdentifiedModelException();
        }

        this.checkSubject(topic);

        final TopicModelInterface topicModel = this.topicsMapper.map(topic, TopicModel.class);

        this.topicsService.save(topicModel);

        return this.topicsMapper.map(topicModel, Identity.class);
    }

    private void checkSubject(Topic topic) throws ForbiddenException {
        final SubjectModelInterface subjectModel = this.subjectsService.find(
                topic.getSubject().getId(),
                this.subjectsOptionsBuilder.forAuth()
                                );
        if (!this.acl.hasAccess(subjectModel)) {
            throw new ForbiddenException();
        }

    }

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

    /**
     * delete(topicId)
     */
}
