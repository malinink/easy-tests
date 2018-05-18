package easytests.api.v1.controllers;

import easytests.api.v1.mappers.TopicsMapper;
import easytests.core.options.builder.TopicsOptionsBuilderInterface;
import easytests.core.services.TopicsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private TopicsOptionsBuilderInterface topicsOptions;

    @Autowired
    @Qualifier("TopicsMapperV1")
    private TopicsMapper usersMapper;

    /**
     * list
     */
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
