package easytests.services;

import easytests.entities.QuestionEntity;
import easytests.mappers.QuestionsMapper;
import easytests.models.QuestionModel;
import easytests.models.QuestionModelInterface;
import easytests.models.TopicModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;

import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

/**
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuestionsMapper questionsMapper;

    @Autowired
    private QuestionsService questionsService;

    private QuestionModelInterface createQuestionModel(Integer id, String text, Integer type,  Integer topicId) {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.setId(id);
        questionModel.setText(text);
        questionModel.setType(type);
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        Mockito.when(topicModel.getId()).thenReturn(topicId);
        questionModel.setTopic(topicModel);
        return questionModel;
    }

    private QuestionEntity createQuestionEntityMock(Integer id, String text, Integer type, Integer topicId) {
        final QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);
        Mockito.when(questionEntity.getId()).thenReturn(id);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getType()).thenReturn(type);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);
        return questionEntity;
    }

    private QuestionModelInterface mapQuestionModel(QuestionEntity questionEntity) {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(questionEntity);
        return questionModel;
    }

    private QuestionEntity mapQuestionEntity(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.map(questionModel);
        return questionEntity;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<QuestionEntity> questionsEntities = new ArrayList<>(2);
        final QuestionEntity questionEntityFirst = this.createQuestionEntityMock(1, "Text1", 1, 1);
        final QuestionEntity questionEntitySecond = this.createQuestionEntityMock(2, "Text2", 2, 2);
        questionsEntities.add(questionEntityFirst);
        questionsEntities.add(questionEntitySecond);
        given(this.questionsMapper.findAll()).willReturn(questionsEntities);

        final List<QuestionModelInterface> questionsModels = this.questionsService.findAll();

        Assert.assertEquals(2, questionsModels.size());
        Assert.assertEquals(questionsModels.get(0), this.mapQuestionModel(questionEntityFirst));
        Assert.assertEquals(questionsModels.get(1), this.mapQuestionModel(questionEntitySecond));
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.questionsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<QuestionModelInterface> questionsModels = this.questionsService.findAll();

        Assert.assertEquals(0, questionsModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final QuestionEntity questionEntity = this.createQuestionEntityMock(id, "NewText", 1, 1);
        given(this.questionsMapper.find(id)).willReturn(questionEntity);

        final QuestionModelInterface questionModel = this.questionsService.find(id);

        Assert.assertEquals(this.mapQuestionModel(questionEntity), questionModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.questionsMapper.find(id)).willReturn(null);

        final QuestionModelInterface questionModel = this.questionsService.find(id);

        Assert.assertEquals(null, questionModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(null, "Text", 1, 1);

        this.questionsService.save(questionModel);

        verify(this.questionsMapper, times(1)).insert(this.mapQuestionEntity(questionModel));
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(1, "Text", 1, 1);

        this.questionsService.save(questionModel);

        verify(this.questionsMapper, times(1)).update(this.mapQuestionEntity(questionModel));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(1, "Text", 1, 1);

        this.questionsService.delete(questionModel);

        verify(this.questionsMapper, times(1)).delete(this.mapQuestionEntity(questionModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(null, "Text", 1, 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.questionsService.delete(questionModel);
    }
}
