package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody Question question) throws BadRequestException {
        System.err.println("TROLALALALA000 " + question.getAnswers().get(0).getText());
        if (question.getId() != null) {
            throw new IdentifiedModelException();
        }
        System.err.println("TROLALALALA " + question.getAnswers().get(0).getText());
        if (question.getAnswers() == null) {
            throw new BadRequestException("Answers must be not absent");
        }

       //VALIDATOR

        final QuestionModelInterface questionModel = this.questionsMapper.map(question, QuestionModel.class);
        System.err.println("TROLOLOLO111 " + questionModel.getAnswers().get(0).getTxt());
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

    private void questionAnswersValidator(QuestionModelInterface questionModel) {

    }
}
