package easytests.core.services;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.mappers.QuestionTypesMapper;
import easytests.core.models.QuestionTypeModel;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.options.QuestionTypesOptionsInterface;
import easytests.support.Entities;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

/**
 * @author ielay
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

    private QuestionTypeModelInterface mapQuestionTypeModel(QuestionTypeEntity questionTypeEntity) {
        final QuestionTypeModelInterface questionTypeModel = new QuestionTypeModel();
        questionTypeModel.map(questionTypeEntity);
        return questionTypeModel;
    }

    @Test
    public void testFindById() throws Exception {
        final Integer id = 1;
        final QuestionTypeEntity questionTypeEntityMock = Entities.createQuestionTypeEntityMock(
                id,
                "name"
        );
        given(this.questionTypesMapper.find(id)).willReturn(questionTypeEntityMock);

        final QuestionTypeModelInterface questionTypeModel = this.questionTypesService.find(id);

        Assert.assertEquals(this.mapQuestionTypeModel(questionTypeEntityMock), questionTypeModel);
    }

    @Test
    public void testFindByIdReturnsNull() throws Exception {
        final Integer id = 10;

        given(this.questionTypesMapper.find(id)).willReturn(null);

        final QuestionTypeModelInterface questionTypeModel = this.questionTypesService.find(id);

        Assert.assertEquals(null, questionTypeModel);
    }

    @Test
    public void testFindAll() throws Exception {
        final List<QuestionTypeEntity> questionTypeEntities = this.getQuestionTypeEntities();

        given(this.questionTypesMapper.findAll()).willReturn(questionTypeEntities);

        final List<QuestionTypeModelInterface> questionTypeModels = this.questionTypesService.findAll();

        Assert.assertEquals(this.getQuestionTypeModels(), questionTypeModels);
    }

    @Test
    public void testFindAllReturnsNull() throws Exception {
        given(this.questionTypesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<QuestionTypeModelInterface> questionTypeModels = this.questionTypesService.findAll();

        Assert.assertEquals(0, questionTypeModels.size());
    }

    @Test
    public void testFindByIdWithOptions() throws Exception {
        final Integer id = 7;
        final QuestionTypeEntity questionTypeEntity = Entities.createQuestionTypeEntityMock(
                id,
                "name"
        );

        final QuestionTypeModelInterface questionTypeModel = this.mapQuestionTypeModel(questionTypeEntity);
        final QuestionTypesOptionsInterface questionTypeOptions = Mockito.mock(QuestionTypesOptionsInterface.class);

        given(this.questionTypesMapper.find(id)).willReturn(questionTypeEntity);
        given(questionTypeOptions.withRelations(questionTypeModel)).willReturn(questionTypeModel);

        final QuestionTypeModelInterface foundedQuestionTypeModel =
                this.questionTypesService.find(id, questionTypeOptions);

        Assert.assertEquals(foundedQuestionTypeModel, questionTypeModel);

        Mockito.verify(questionTypeOptions).withRelations(questionTypeModel);
    }

    @Test
    public void findAllWithOptions() throws Exception {
        final List<QuestionTypeEntity> questionTypeEntities = this.getQuestionTypeEntities();
        final List<QuestionTypeModelInterface> questionTypeModels = this.getQuestionTypeModels();
        final QuestionTypesOptionsInterface questionTypesOptionsMock =
                Mockito.mock(QuestionTypesOptionsInterface.class);

        given(this.questionTypesMapper.findAll()).willReturn(questionTypeEntities);
        given(questionTypesOptionsMock.withRelations(questionTypeModels)).willReturn(questionTypeModels);

        final List<QuestionTypeModelInterface> foundedQuestionTypeModel =
                this.questionTypesService.findAll(questionTypesOptionsMock);

        Assert.assertEquals(questionTypeModels, foundedQuestionTypeModel);

        Mockito.verify(questionTypesOptionsMock).withRelations(questionTypeModels);
    }

    private List<QuestionTypeEntity> getQuestionTypeEntities(){
        final List<QuestionTypeEntity> questionTypeEntities = new ArrayList<>(2);
        questionTypeEntities.add(Entities.createQuestionTypeEntityMock(
                1,
                "name1"
        ));
        questionTypeEntities.add(Entities.createQuestionTypeEntityMock(
                2,
                "name2"
        ));
        return questionTypeEntities;
    }

    private List<QuestionTypeModelInterface> getQuestionTypeModels(){
        final List<QuestionTypeModelInterface> questionTypeModels = new ArrayList<>(2);
        for(QuestionTypeEntity questionTypeEntity: this.getQuestionTypeEntities()){
            questionTypeModels.add(this.mapQuestionTypeModel(questionTypeEntity));
        }
        return questionTypeModels;
    }
}
