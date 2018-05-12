package easytests.api.v1.controllers;

import easytests.api.v1.mappers.TopicsMapper;
import easytests.api.v1.models.Topic;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.TopicsOptionsBuilderInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lelay
 */
@RestController("TopicsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/topics")
public class TopicsController {

    @Autowired
    protected TopicsServiceInterface topicsService;

    @Autowired
    private TopicsOptionsBuilderInterface topicsOptionsBuilder;

    @Autowired
    @Qualifier("TopicsMapperV1")
    private TopicsMapper topicsMapper;

    @GetMapping("")
    public List<Topic> list(@RequestParam(required = true) Integer subjectId) {
        final List<TopicModelInterface> topicsModels =
                this.topicsService.findBySubject(new SubjectModelEmpty(subjectId));

        //todo: ACL should be there

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
    /**
     * show(topicId)
     */
    /**
     * delete(topicId)
     */
}
