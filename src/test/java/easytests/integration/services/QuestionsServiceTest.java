package easytests.integration.services;

import easytests.core.models.*;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.QuestionTypeModelEmpty;
import easytests.core.models.empty.TopicModelEmpty;
import easytests.core.services.QuestionsService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author firkhraag
 */
public class QuestionsServiceTest extends AbstractServiceTest {

    @Autowired
    private QuestionsService questionsService;

    private QuestionModelInterface createQuestionModel(Integer id, String text, Integer questionTypeId, Integer topicId) {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.setId(id);
        questionModel.setText(text);
        questionModel.setQuestionType(new QuestionTypeModelEmpty(questionTypeId));
        questionModel.setAnswers(new ModelsListEmpty());
        questionModel.setTopic(new TopicModelEmpty(topicId));
        return questionModel;
    }

    @Test
    public void testSaveModel() throws Exception {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.setText("test1");
        questionModel.setQuestionType(new QuestionTypeModelEmpty(1));
        questionModel.setAnswers(new ModelsListEmpty());
        questionModel.setTopic(new TopicModelEmpty(1));

        this.questionsService.save(questionModel);

        final QuestionModelInterface foundedQuestionModel = this.questionsService.find(questionModel.getId());

        Assert.assertEquals(questionModel, foundedQuestionModel);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final QuestionModelInterface questionModel = this.createQuestionModel(id, "test1" , 1, 1);

        final QuestionModelInterface foundedQuestionModel = this.questionsService.find(id);

        Assert.assertEquals(questionModel.getId(), foundedQuestionModel.getId());
        Assert.assertEquals(questionModel.getText(), foundedQuestionModel.getText());
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final QuestionModelInterface questionModel = this.questionsService.find(id);

        Assert.assertEquals(null, questionModel);
    }
}
