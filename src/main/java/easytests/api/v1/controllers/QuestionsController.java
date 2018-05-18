package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuestionsMapper;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author RisaMagpie
 */
@RestController("QuestionControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/questions")
public class QuestionsController {

    @Autowired
    protected QuestionsService questionsService;

    @Autowired
    protected QuestionsOptionsBuilder questionsOptions;

    @Autowired
    @Qualifier("QuestionsMapperV1")
    private QuestionsMapper questionsMapper;

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
     * show(questionId)
     */
    /**
     * delete(questionId)
     */

}
