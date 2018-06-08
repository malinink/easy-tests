package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.*;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.AnswersOptions;
import easytests.core.options.QuestionsOptions;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.options.builder.QuestionsOptionsBuilderInterface;
import easytests.core.options.builder.TopicsOptionsBuilderInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
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
    protected TopicsServiceInterface topicsService;

    @Autowired
    protected QuestionsOptionsBuilderInterface questionsOptionsBuilder;

    @Autowired
    protected TopicsOptionsBuilderInterface topicsOptionsBuilder;

    @Autowired
    @Qualifier("QuestionsMapperV1")
    private QuestionsMapper questionsMapper;

    @GetMapping
    public List<Question> list(@RequestParam(name = "topicId", required = true) Integer topicId)
            throws NotFoundException, ForbiddenException {
        final TopicModelInterface topicModel = this.topicsService.find(topicId, this.topicsOptionsBuilder.forAuth());

        if (topicModel == null) {
            throw new NotFoundException();
        }

        if (!this.acl.hasAccess(topicModel)) {
            throw new ForbiddenException();
        }

        final List<QuestionModelInterface> questionsModels =
                this.questionsService.findByTopic(topicModel, new QuestionsOptions().withAnswers(new AnswersOptions()));

        return questionsModels
                .stream()
                .map(model -> this.questionsMapper.map(model, Question.class))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody Question question) throws Exception {
        if (question.getId() != null) {
            throw new IdentifiedModelException();
        }

        this.checkTopic(question.getTopic().getId());

        final QuestionModelInterface questionModel = this.questionsMapper.map(question, QuestionModel.class);

        this.questionsService.save(questionModel, new QuestionsOptions().withAnswers(new AnswersOptions()));

        return this.questionsMapper.map(questionModel, Identity.class);
    }

    @PutMapping("")
    public void update(@RequestBody Question question)
            throws ForbiddenException, NotFoundException, BadRequestException {
        if (question.getId() == null) {
            throw new UnidentifiedModelException();
        }

        final QuestionModelInterface questionModel = this.getQuestionModel(question.getId());

        if (!this.acl.hasAccess(questionModel)) {
            throw new ForbiddenException();
        }
        this.checkTopic(question.getTopic().getId());

        final QuestionsOptionsInterface questionOptionWithAnswers =
                new QuestionsOptions().withAnswers(new AnswersOptions());

        final QuestionModelInterface questionModelWithAnswers =
                this.questionsService.find(question.getId(), questionOptionWithAnswers);

        this.questionsMapper.map(question, questionModelWithAnswers);
        this.questionsService.save(questionModelWithAnswers, questionOptionWithAnswers);
    }

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

    @DeleteMapping("/{questionId}")
    public void delete(@PathVariable Integer questionId) throws NotFoundException, ForbiddenException {
        final QuestionModelInterface questionModel = this.getQuestionModel(questionId);
        final QuestionsOptionsInterface questionOption = this.questionsOptionsBuilder.forDelete();

        if (!this.acl.hasAccess(questionModel)) {
            throw new ForbiddenException();
        }

        final QuestionModelInterface questionModelForDelete = this.questionsService.find(questionId, questionOption);
        this.questionsService.delete(questionModelForDelete, questionOption);
    }

    private void checkTopic(Integer topicId) throws BadRequestException {
        final TopicModelInterface topicModel = this.topicsService
                .find(topicId, this.topicsOptionsBuilder.forAuth());

        if (!this.acl.hasAccess(topicModel)) {
            throw new BadRequestException();
        }
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
}
