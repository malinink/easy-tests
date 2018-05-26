package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
import easytests.core.models.AnswerModelInterface;
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
public class QuestionsController extends AbstractController{

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

       //VALIDATOR

        final QuestionModelInterface questionModel = this.questionsMapper.map(question, QuestionModel.class);

        this.questionAnswersValidator(questionModel);

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

    private void questionAnswersValidator(QuestionModelInterface questionModel) throws Exception {
        int rightAnswersCount = 0;

        for (AnswerModelInterface answerModel: questionModel.getAnswers()) {
            this.answerValidator(answerModel);
            if (answerModel.getRight()) {
                ++rightAnswersCount;
            }
        }
        switch (questionModel.getQuestionType().getId()) {
            case 1: {
                if (rightAnswersCount == 0) {
                    throw new BadRequestException("Right Answer must be exist");
                }
                if (rightAnswersCount > 1) {
                    throw new BadRequestException("This Question can have only one right Answer");
                }
                break;
            }
            case 2: {
                if (rightAnswersCount == 0) {
                    throw new BadRequestException("Right Answers must be exist");
                }
                if (rightAnswersCount == 1) {
                    throw new BadRequestException("This Question can't have only one right Answer");
                }
                if (rightAnswersCount == questionModel.getAnswers().size()) {
                    throw new BadRequestException("This Question can't have all right Answers");
                }
                break;
            }
            /**
             * TODO
             * Add check for Numeric And Text Answers
             */
            default: break;
        }
    }

    private void answerValidator(AnswerModelInterface answerModel) throws Exception {
        if (!(answerModel.getId() == null)) {
            throw new BadRequestException("Answer Id must be absent");
        }
        if (!answerModel.getTxt().getClass().getName().equals("String".getClass().getName())) {
            throw new BadRequestException("Text in Answer must be String");
        }
        if (answerModel.getTxt().equals("") || answerModel.getTxt() == null) {
            throw new BadRequestException("Text in answer must be not absent");
        }
        if (!answerModel.getRight().getClass().getName().equals(Boolean.class.getName())) {
            throw new BadRequestException("Type of isRight must be boolean");
        }
        if (!answerModel.getSerialNumber().getClass().getName().equals(Integer.class.getName())) {
            throw new BadRequestException("Serial Number must be Integer");
        }
    }
}
