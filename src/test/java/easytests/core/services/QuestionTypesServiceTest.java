package easytests.core.services;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.mappers.QuestionTypesMapper;
import easytests.core.models.QuestionTypeModel;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.support.Entities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testFindById(){
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

}
