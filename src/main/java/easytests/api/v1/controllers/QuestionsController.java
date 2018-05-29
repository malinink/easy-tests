package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.*;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.api.v1.models.Question;
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
    
    /**
     * create
     */

    @PutMapping("")
    public void update(@RequestBody Question question) throws NotFoundException, BadRequestException {
        if (question.getId() == null) {
            throw new UnidentifiedModelException();
        }

        final QuestionModelInterface questionModel = this.getQuestionModel(question.getId());

        this.checkTopic(question);

        this.questionsMapper.map(question, questionModel);

        this.questionsService.save(questionModel);
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

    private void checkTopic(Question question) throws BadRequestException {
        final TopicModelInterface topicModel = this.topicsService.find(
                question.getTopic().getId(),
                this.topicsOptionsBuilder.forAuth()
        );

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
