package easytests.core.services;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.mappers.QuestionTypesMapper;
import easytests.core.models.QuestionTypeModel;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.support.Entities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        final QuestionTypeEntity questionTypeEntityMock = Entities.createQuestionTypeEntity(
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

    private List<QuestionTypeEntity> getQuestionTypeEntities(){
        final List<QuestionTypeEntity> questionTypeEntities = new ArrayList<>(2);
        questionTypeEntities.add(Entities.createQuestionTypeEntity(
                1,
                "name1"
        ));
        questionTypeEntities.add(Entities.createQuestionTypeEntity(
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
