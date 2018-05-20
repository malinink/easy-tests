package easytests.core.services;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.mappers.QuestionTypesMapper;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.options.QuestionTypesOptionsInterface;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.support.QuestionTypesSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;


/**
 * @author VlasovIgor
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTypesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuestionTypesMapper questionTypesMapper;

    @Autowired
    private QuestionTypesService questionTypesService;

    private QuestionTypesSupport questionTypesSupport = new QuestionTypesSupport();

    private List<QuestionTypeEntity> getQuestionTypesFixturesEntities() {
        final List<QuestionTypeEntity> questionTypesEntities = new ArrayList<>(2);
        questionTypesEntities.add(this.questionTypesSupport.getEntityFixtureMock(0));
        questionTypesEntities.add(this.questionTypesSupport.getEntityFixtureMock(1));
        return questionTypesEntities;
    }

    private List<QuestionTypeModelInterface> getQuestionTypesFixturesModels() {
        final List<QuestionTypeModelInterface> questionTypesModels = new ArrayList<>(2);
        questionTypesModels.add(this.questionTypesSupport.getModelFixtureMock(0));
        questionTypesModels.add(this.questionTypesSupport.getModelFixtureMock(1));
        return questionTypesModels;
    }

    private void assertServicesSet(QuestionTypesOptionsInterface questionTypesOptions) {
        this.assertServicesSet(questionTypesOptions, 1);
    }

    private void assertServicesSet(QuestionTypesOptionsInterface questionTypesOptions, Integer times) {
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<QuestionTypeEntity> questionTypesEntities = this.getQuestionTypesFixturesEntities();
        when(this.questionTypesMapper.findAll()).thenReturn(questionTypesEntities);

        final List<QuestionTypeModelInterface> questionTypesFoundedModels = this.questionTypesService.findAll();

        this.questionTypesSupport.assertModelsListEquals(this.getQuestionTypesFixturesModels(), questionTypesFoundedModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.questionTypesMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<QuestionTypeModelInterface> questionTypesFoundedModels = this.questionTypesService.findAll();

        Assert.assertNotNull(questionTypesFoundedModels);
        Assert.assertEquals(0, questionTypesFoundedModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<QuestionTypeModelInterface> questionTypesModels = this.getQuestionTypesFixturesModels();
        final List<QuestionTypeEntity> questionTypesEntities = this.getQuestionTypesFixturesEntities();
        final QuestionTypesOptionsInterface questionTypesOptions = mock(QuestionTypesOptionsInterface.class);
        when(this.questionTypesMapper.findAll()).thenReturn(questionTypesEntities);
        when(questionTypesOptions.withRelations(listCaptor.capture())).thenReturn(questionTypesModels);

        final List<QuestionTypeModelInterface> questionTypesFoundedModels = this.questionTypesService.findAll(questionTypesOptions);

        this.assertServicesSet(questionTypesOptions);
        this.questionTypesSupport.assertModelsListEquals(questionTypesModels, listCaptor.getValue());
        Assert.assertSame(questionTypesModels, questionTypesFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final QuestionTypeEntity questionTypeEntity = this.questionTypesSupport.getEntityFixtureMock(0);
        when(this.questionTypesMapper.find(questionTypeEntity.getId())).thenReturn(questionTypeEntity);

        final QuestionTypeModelInterface questionTypeFoundedModel = this.questionTypesService.find(questionTypeEntity.getId());

        this.questionTypesSupport.assertEquals(this.questionTypesSupport.getModelFixtureMock(0), questionTypeFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 12;
        when(this.questionTypesMapper.find(id)).thenReturn(null);

        final QuestionTypeModelInterface questionTypeFoundedModel = this.questionTypesService.find(id);

        Assert.assertNull(questionTypeFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<QuestionTypeModelInterface> questionTypeModelCaptor = ArgumentCaptor.forClass(QuestionTypeModelInterface.class);
        final QuestionTypeModelInterface questionTypeModel = this.questionTypesSupport.getModelFixtureMock(0);
        final QuestionTypeEntity questionTypeEntity = this.questionTypesSupport.getEntityFixtureMock(0);
        final QuestionTypesOptionsInterface questionTypesOptions = mock(QuestionTypesOptionsInterface.class);
        when(this.questionTypesMapper.find(questionTypeModel.getId())).thenReturn(questionTypeEntity);
        when(questionTypesOptions.withRelations(questionTypeModelCaptor.capture())).thenReturn(questionTypeModel);

        final QuestionTypeModelInterface questionTypeFoundedModel = this.questionTypesService.find(questionTypeModel.getId(), questionTypesOptions);

        this.assertServicesSet(questionTypesOptions);
        this.questionTypesSupport.assertEquals(questionTypeModel, questionTypeModelCaptor.getValue());
        Assert.assertSame(questionTypeModel, questionTypeFoundedModel);
    }

}
