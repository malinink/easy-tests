package easytests.integration.services;

import easytests.models.*;
import easytests.models.empty.QuestionModelEmpty;
import easytests.options.AnswersOptions;
import easytests.options.QuestionsOptions;
import easytests.services.AnswersService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author rezenbekk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class AnswersServiceTest {
    @Autowired
    private AnswersService answersService;

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

        final AnswerModelInterface buildAnswerModel
                = this.answersService.find(id, new AnswersOptions().withQuestion(new QuestionsOptions()));

        Assert.assertEquals(answerModel, buildAnswerModel);
        Assert.assertEquals(questionModel, answerModel.getQuestion());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final Integer id = this.answersService.findAll().size() + 1;
        final AnswerModelInterface answerModel = Models.createAnswerModel(null, "New answer", 5, 1, true);

        this.answersService.save(answerModel);
        final AnswerModelInterface builtAnswerModel = this.answersService.find(id);

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

        Assert.assertEquals(answerModel, builtAnswerModel);

    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final AnswerModelInterface answerModel = this.createAnswerModel(id, "Answer1", 1, 1, true);

        final AnswerModelInterface builtAnswerModel = this.answersService.find(id);

        Assert.assertEquals(answerModel.getId(), builtAnswerModel.getId());
        Assert.assertEquals(answerModel.getTxt(), builtAnswerModel.getTxt());
        Assert.assertEquals(answerModel.getSerialNumber(), builtAnswerModel.getSerialNumber());
        Assert.assertEquals(answerModel.getRight(), builtAnswerModel.getRight());
    }
}
