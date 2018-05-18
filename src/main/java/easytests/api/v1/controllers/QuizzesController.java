package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuizzesMapper;
import easytests.core.options.builder.QuizzesOptionsBuilderInterface;
import easytests.core.services.QuizzesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author miron97
 */
@RestController("QuizzesControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/quizzes")
public class QuizzesController {

    @Autowired
    protected QuizzesServiceInterface quizzesService;

    @Autowired
    private QuizzesOptionsBuilderInterface quizzesOptions;

    @Autowired
    @Qualifier("QuizzesMapperV1")
    private QuizzesMapper quizzesMapper;

    /**
     * list
     */
    /**
     * show(quizId)
     */
}
