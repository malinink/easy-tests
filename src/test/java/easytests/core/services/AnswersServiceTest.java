package easytests.core.services;

import easytests.core.entities.AnswerEntity;
import easytests.core.mappers.AnswersMapper;
import easytests.core.models.AnswerModel;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.AnswersOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;

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
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswersServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private AnswersMapper answersMapper;

    @Autowired
    private AnswersService answersService;

    private AnswerModelInterface createAnswerModel(Integer id, String txt,
                                                   QuestionModelInterface question, Integer serialNumber,
                                                   boolean right) {
        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.setId(id);
        answerModel.setTxt(txt);
        answerModel.setQuestion(question);
        answerModel.setSerialNumber(serialNumber);
        answerModel.setRight(right);
        return answerModel;
    }

    private AnswerModelInterface createAnswerModel(Integer id, String txt,
                                                   Integer questionId, Integer serialNumber, Boolean right) {

        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.setId(id);
        answerModel.setTxt(txt);
        answerModel.setSerialNumber(serialNumber);
        answerModel.setRight(true);

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);

        answerModel.setQuestion(questionModel);

        return answerModel;

    }

    private AnswerEntity createAnswerEntityMock(Integer id, String txt,
                                                Integer questionId, Integer serialNumber, boolean right) {
        final AnswerEntity answerEntity = Mockito.mock(AnswerEntity.class);
        Mockito.when(answerEntity.getId()).thenReturn(id);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.getSerialNumber()).thenReturn(serialNumber);
        Mockito.when(answerEntity.getRight()).thenReturn(right);
        return answerEntity;
    }

    private AnswerModelInterface mapAnswerModel(AnswerEntity answerEntity) {
        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.map(answerEntity);
        return answerModel;
    }

    private AnswerEntity mapAnswerEntity(AnswerModelInterface answerModel) {
        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.map(answerModel);
        return answerEntity;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<AnswerEntity> answersEntities = new ArrayList<>(2);
        final AnswerEntity answerEntityFirst = this.createAnswerEntityMock(1, "Answer1", 1, 1, true);
        final AnswerEntity answerEntitySecond = this.createAnswerEntityMock(2, "Answer2", 2, 2, false);
        answersEntities.add(answerEntityFirst);
        answersEntities.add(answerEntitySecond);
        given(this.answersMapper.findAll()).willReturn(answersEntities);

        final List<AnswerModelInterface> answersModels = this.answersService.findAll();

        Assert.assertEquals(2, answersModels.size());
        Assert.assertEquals(answersModels.get(0), this.mapAnswerModel(answerEntityFirst));
        Assert.assertEquals(answersModels.get(1), this.mapAnswerModel(answerEntitySecond));
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.answersMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<AnswerModelInterface> answersModels = this.answersService.findAll();

        Assert.assertEquals(0, answersModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final AnswerEntity answerEntity = this.createAnswerEntityMock(id, "NewAnswer", 1, 1, true);
        given(this.answersMapper.find(id)).willReturn(answerEntity);

        final AnswerModelInterface answerModel = this.answersService.find(id);

        Assert.assertEquals(this.mapAnswerModel(answerEntity), answerModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final AnswerEntity answerEntity = this.createAnswerEntityMock(id, "NewAnswer", 1, 1, true);
        final AnswerModelInterface answerModel = this.mapAnswerModel(answerEntity);

        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        given(this.answersMapper.find(id)).willReturn(answerEntity);
        given(answersOptions.withRelations(answerModel)).willReturn(answerModel);

        final AnswerModelInterface foundedAnswerModel
                = this.answersService.find(id, answersOptions);

        verify(answersOptions).withRelations(answerModel);
        Assert.assertNotNull(foundedAnswerModel);
        Assert.assertEquals(answerModel, foundedAnswerModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.answersMapper.find(id)).willReturn(null);

        final AnswerModelInterface answerModel = this.answersService.find(id);

        Assert.assertEquals(null, answerModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final AnswerModelInterface answerModel = this.createAnswerModel(null, "Answer11", 1, 1, true);
        doAnswer(invocation -> {
            final AnswerEntity answerEntity = (AnswerEntity) invocation.getArguments()[0];
            answerEntity.setId(100);
            return null;
        }).when(this.answersMapper).insert(Mockito.any(AnswerEntity.class));
        this.answersService.save(answerModel);

        // TODO verify(this.answersMapper, times(1)).insert(this.mapAnswerEntity(answerModel));
        Assert.assertEquals((Integer) 100, answerModel.getId());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final AnswerModelInterface answerModel = this.createAnswerModel(1, "Answer22", 1, 1, true);

        this.answersService.save(answerModel);

        verify(this.answersMapper, times(1)).update(this.mapAnswerEntity(answerModel));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final AnswerModelInterface answerModel = this.createAnswerModel(1, "Answer3", 1, 1, true);

        this.answersService.delete(answerModel);

        verify(this.answersMapper, times(1)).delete(this.mapAnswerEntity(answerModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final AnswerModelInterface answerModel = this.createAnswerModel(null, "Answer4", 1, 1, true);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.answersService.delete(answerModel);
    }

}
