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

import easytests.support.AnswersSupport;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import org.mockito.ArgumentCaptor;
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

    private AnswersSupport answersSupport = new AnswersSupport();

    private List<AnswerEntity> getAnswersFixturesEntities() {
        final List<AnswerEntity> answersEntities = new ArrayList<>(2);
        answersEntities.add(this.answersSupport.getEntityFixtureMock(0));
        answersEntities.add(this.answersSupport.getEntityFixtureMock(1));
        return answersEntities;
    }

    private List<AnswerModelInterface> getAnswersFixturesModels() {
        final List<AnswerModelInterface> answersModels = new ArrayList<>(2);
        answersModels.add(this.answersSupport.getModelFixtureMock(0));
        answersModels.add(this.answersSupport.getModelFixtureMock(1));
        return answersModels;
    }

    private void assertEquals(List<AnswerModelInterface> expected, List<AnswerModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (AnswerModelInterface answerModel: expected) {
            this.answersSupport.assertEquals(answerModel, actual.get(i));
            i++;
        }
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<AnswerEntity> answersEntities = this.getAnswersFixturesEntities();
        when(this.answersMapper.findAll()).thenReturn(answersEntities);

        final List<AnswerModelInterface> answersFoundedModels = this.answersService.findAll();

        this.assertEquals(this.getAnswersFixturesModels(), answersFoundedModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.answersMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<AnswerModelInterface> answersFoundedModels = this.answersService.findAll();

        Assert.assertNotNull(answersFoundedModels);
        Assert.assertEquals(0, answersFoundedModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final AnswerEntity answerEntity = this.answersSupport.getEntityFixtureMock(0);
        when(this.answersMapper.find(answerEntity.getId())).thenReturn(answerEntity);

        final AnswerModelInterface answerFoundedModel = this.answersService.find(answerEntity.getId());

        this.answersSupport.assertEquals(this.answersSupport.getModelFixtureMock(0), answerFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<AnswerModelInterface> answerModelCaptor = ArgumentCaptor.forClass(AnswerModelInterface.class);
        final AnswerModelInterface answerModel = this.answersSupport.getModelFixtureMock(0);
        final AnswerEntity answerEntity = this.answersSupport.getEntityFixtureMock(0);
        final AnswersOptionsInterface answersOptions = mock(AnswersOptionsInterface.class);
        when(this.answersMapper.find(answerModel.getId())).thenReturn(answerEntity);
        when(answersOptions.withRelations(answerModelCaptor.capture())).thenReturn(answerModel);

        final AnswerModelInterface answerFoundedModel = this.answersService.find(answerModel.getId(), answersOptions);

        this.answersSupport.assertEquals(answerModel, answerModelCaptor.getValue());
        Assert.assertSame(answerModel, answerFoundedModel);
        verify(this.answersMapper, times(1)).find(answerModel.getId());
        verifyNoMoreInteractions(this.answersMapper);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer absentId = 12;
        when(this.answersMapper.find(absentId)).thenReturn(null);

        final AnswerModelInterface answerFoundedModel = this.answersService.find(absentId);

        Assert.assertNull(answerFoundedModel);
    }

   // @Test
   // public void testFindByQuestion() throws  Exception {
        
    //}

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<AnswerEntity> answerEntityCaptor = ArgumentCaptor.forClass(AnswerEntity.class);

        this.answersService.save(this.answersSupport.getModelAdditionalMock(0));

        verify(this.answersMapper, times(1)).insert(answerEntityCaptor.capture());
        this.answersSupport.assertEquals(this.answersSupport.getEntityAdditionalMock(0), answerEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<AnswerEntity> answerEntityCaptor = ArgumentCaptor.forClass(AnswerEntity.class);

        this.answersService.save(this.answersSupport.getModelFixtureMock(0));

        verify(this.answersMapper, times(1)).update(answerEntityCaptor.capture());
        this.answersSupport.assertEquals(this.answersSupport.getEntityFixtureMock(0), answerEntityCaptor.getValue());
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<AnswerEntity> answerEntityCaptor = ArgumentCaptor.forClass(AnswerEntity.class);

        this.answersService.delete(this.answersSupport.getModelFixtureMock(0));

        verify(this.answersMapper, times(1)).delete(answerEntityCaptor.capture());
        this.answersSupport.assertEquals(this.answersSupport.getEntityFixtureMock(0), answerEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final AnswerModelInterface answerModel = this.answersSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.answersService.delete(answerModel);
    }

}
