package easytests.integration.services;

import easytests.core.models.*;
import easytests.core.models.empty.QuestionModelEmpty;
import easytests.core.options.AnswersOptions;
import easytests.core.options.QuestionsOptions;
import easytests.core.services.AnswersService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author rezenbekk
 */
public class AnswersServiceTest extends AbstractServiceTest {

    @Autowired
    private AnswersService answersService;

    @Test
    public void testSaveModel() throws Exception {

        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.setTxt("Answer text");
        answerModel.setSerialNumber(1);
        answerModel.setQuestion(new QuestionModelEmpty(1));
        answerModel.setRight(true);

        this.answersService.save(answerModel);

        final AnswerModelInterface builtAnswerModel = this.answersService.find(answerModel.getId());

        Assert.assertEquals(answerModel, builtAnswerModel);

    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final AnswerModelInterface answerModel = this.answersService.find(id);

        Assert.assertEquals(null, answerModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer questionId = 1;

        final AnswerModelInterface answerModel = Models.createAnswerModel(id, "Answer1", 1, 1, true);
        final QuestionModelInterface questionModel = Models.createQuestionModel(questionId, "test1", 1, 1);
        answerModel.setQuestion(questionModel);

        final AnswerModelInterface builtAnswerModel
                = this.answersService.find(id, new AnswersOptions().withQuestion(new QuestionsOptions()));

        Assert.assertEquals(answerModel, builtAnswerModel);
        Assert.assertEquals(questionModel, answerModel.getQuestion());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final AnswerModelInterface answerModel = Models.createAnswerModel(null, "New answer", 5, 1, true);

        this.answersService.save(answerModel);
        Assert.assertNotNull(answerModel.getId());
        final AnswerModelInterface builtAnswerModel = this.answersService.find(answerModel.getId());

        Assert.assertEquals(answerModel, builtAnswerModel);
    }

    @Test
    public void testSaveUpdatesModel() throws Exception {
        final Integer id = 1;
        final AnswerModelInterface answerModel = Models.createAnswerModel(id, "Newest Answer",
                1, 1, true);
        Assert.assertNotEquals(answerModel, this.answersService.find(id));

        this.answersService.save(answerModel);

        Assert.assertEquals(answerModel, this.answersService.find(id));
    }
}
