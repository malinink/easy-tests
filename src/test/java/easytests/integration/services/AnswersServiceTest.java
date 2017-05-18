package easytests.integration.services;

import easytests.models.*;
import easytests.models.empty.QuestionModelEmpty;
import easytests.services.AnswersService;
import easytests.services.QuestionsService;
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

    @Autowired
    private QuestionsService questionsService;

    private AnswerModelInterface createAnswerModel(Integer id, String txt, Integer questionId,
                                                    Integer serialNumber, Boolean right) {

        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.setId(id);
        answerModel.setTxt(txt);
        answerModel.setSerialNumber(serialNumber);
        answerModel.setRight(right);
        answerModel.setQuestion(questionsService.find(questionId));

        return answerModel;
    }

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
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final AnswerModelInterface answerModel = this.createAnswerModel(id, "Answer1", 1, 1, true);

        final AnswerModelInterface builtAnswerModel = this.answersService.find(id);

        Assert.assertEquals(answerModel.getId(), builtAnswerModel.getId());
        Assert.assertEquals(answerModel.getTxt(), builtAnswerModel.getTxt());
        Assert.assertEquals(answerModel.getSerialNumber(), builtAnswerModel.getSerialNumber());
        Assert.assertEquals(answerModel.getRight(), builtAnswerModel.getRight());
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final AnswerModelInterface answerModel = this.answersService.find(id);

        Assert.assertEquals(null, answerModel);
    }
}
