package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
import easytests.api.v1.validators.*;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author RisaMagpie
 */
@RestController("QuestionsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/questions")
public class QuestionsController extends AbstractController {

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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody Question question) throws Exception {

        if (question.getId() != null) {
            throw new IdentifiedModelException();
        }

        if (question.getAnswers() == null) {
            throw new BadRequestException("Answers must be not absent");
        }

        final QuiestionsValidator quiestionsValidator = new QuiestionsValidator();

        final QuestionModelInterface questionModel = this.questionsMapper.map(question, QuestionModel.class);

        quiestionsValidator.validateQuestion(questionModel);

        this.questionsService.save(questionModel);

        return this.questionsMapper.map(questionModel, Identity.class);
    }

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
