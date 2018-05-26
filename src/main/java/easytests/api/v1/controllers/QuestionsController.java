package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
import easytests.api.v1.validators.QuiestionsValidator;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.AnswersOptions;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.options.builder.QuestionsOptionsBuilderInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
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
    protected QuestionsServiceInterface questionsService;

    @Autowired
    protected QuestionsOptionsBuilderInterface questionsOptionsBuilder;

    @Autowired
    protected TopicsServiceInterface topicsService;

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
    @GetMapping("/{questionId}")
    public Question show(@PathVariable Integer questionId) throws NotFoundException, ForbiddenException {
        final QuestionModelInterface questionModel = this.getQuestionModel(
                questionId,
                this.questionsOptionsBuilder.forAuth().withAnswers(new AnswersOptions())
        );
        if (!this.acl.hasAccess(questionModel)) {
            throw new ForbiddenException();
        }
        return this.questionsMapper.map(questionModel, Question.class);
    }

    private QuestionModelInterface getQuestionModel(Integer id, QuestionsOptionsInterface
            questionOptions) throws NotFoundException {
        final QuestionModelInterface questionModel = this.questionsService.find(id, questionOptions);
        if (questionModel == null) {
            throw new NotFoundException();
        }
        return questionModel;
    }

    private QuestionModelInterface getQuestionModel(Integer id) throws NotFoundException {
        return this.getQuestionModel(id, this.questionsOptionsBuilder.forAuth());
    }
    /**
     * delete(questionId)
     */
}
