package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.*;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.options.builder.AnswersOptionsBuilderInterface;
import easytests.core.options.builder.QuestionsOptionsBuilderInterface;
import easytests.core.services.AnswersService;
import easytests.core.services.QuestionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author RisaMagpie
 */
@RestController("QuestionControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/questions")
public class QuestionsController extends AbstractController {

    @Autowired
    protected AnswersService answersService;

    @Autowired
    protected QuestionsService questionsService;

    @Autowired
    protected AnswersOptionsBuilderInterface answersOptionsBuilder;

    @Autowired
    protected QuestionsOptionsBuilderInterface questionsOptionsBuilder;

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
    @DeleteMapping("/{questionId}")
    public void delete(@PathVariable Integer questionId) throws NotFoundException, ForbiddenException {
        final QuestionModelInterface questionModel = this.getQuestionModel(questionId);
        final QuestionsOptionsInterface questionOption=this.questionsOptionsBuilder.forDelete();

        if (!this.acl.hasAccess(questionModel)) {
            throw new ForbiddenException();
        }

        final QuestionModelInterface questionModelForDelete = this.questionsService.find(questionId,questionOption);
        this.questionsService.delete(questionModelForDelete,questionOption);
    }

    private QuestionModelInterface getQuestionModel (Integer id,
                                                     QuestionsOptionsInterface questionOptions) throws NotFoundException {
        final QuestionModelInterface questionModel = this.questionsService.find(id, questionOptions);
        if (questionModel == null) {
            throw new NotFoundException();
        }
        return questionModel;
    }

    private QuestionModelInterface getQuestionModel (Integer id) throws NotFoundException {
        return this.getQuestionModel(id, this.questionsOptionsBuilder.forAuth());
    }

}
