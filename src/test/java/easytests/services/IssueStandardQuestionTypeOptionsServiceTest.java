package easytests.services;

import easytests.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.mappers.IssueStandardQuestionTypeOptionsMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModel;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import java.util.ArrayList;
import java.util.List;

import easytests.services.exceptions.DeleteUnidentifiedModelException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardQuestionTypeOptionsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionsMapper;

    @InjectMocks
    private IssueStandardQuestionTypeOptionsService questionTypeOptionsService;

    private IssueStandardQuestionTypeOptionModelInterface createQuestionTypeOptionModel(
            Integer id, Integer questionTypeId, Integer minQuestions,
            Integer maxQuestions, Integer timeLimit, Integer issueStandardId) {

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        questionTypeOptionModel.setId(id);
        questionTypeOptionModel.setQuestionTypeId(questionTypeId);
        questionTypeOptionModel.setMinQuestions(minQuestions);
        questionTypeOptionModel.setMaxQuestions(maxQuestions);
        questionTypeOptionModel.setTimeLimit(timeLimit);
        questionTypeOptionModel.setIssueStandard(issueStandardModel);

        return questionTypeOptionModel;
    }

    private IssueStandardQuestionTypeOptionEntity createQuestionTypeOptionEntityMock(
            Integer id, Integer questionTypeId, Integer minQuestions,
            Integer maxQuestions, Integer timeLimit, Integer issueStandardId) {

        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = Mockito.mock(IssueStandardQuestionTypeOptionEntity.class);

        Mockito.when(questionTypeOptionEntity.getId()).thenReturn(id);
        Mockito.when(questionTypeOptionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionTypeOptionEntity.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(questionTypeOptionEntity.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(questionTypeOptionEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(questionTypeOptionEntity.getIssueStandardId()).thenReturn(issueStandardId);

        return questionTypeOptionEntity;
    }

    private IssueStandardQuestionTypeOptionModelInterface
        mapQuestionTypeOptionModel(IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity) {

        IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.map(questionTypeOptionEntity);
        return questionTypeOptionModel;
    }

    private IssueStandardQuestionTypeOptionEntity
        mapQuestionTypeOptionEntity(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {

        IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = new IssueStandardQuestionTypeOptionEntity();
        questionTypeOptionEntity.map(questionTypeOptionModel);
        return questionTypeOptionEntity;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = new ArrayList<>(2);

        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(1, 1, 10, 20, 60, 1));
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(2, 2, 11, 21, 120, 2));

        given(this.questionTypeOptionsMapper.findAll()).willReturn(questionTypeOptionEntities);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findAll();

        Assert.assertNotNull(questionTypeOptionModels);
        Assert.assertEquals(questionTypeOptionEntities.size(), questionTypeOptionModels.size());
        for (int i = 0; i < questionTypeOptionModels.size(); i++) {
            Assert.assertEquals(questionTypeOptionModels.get(i),
                    this.mapQuestionTypeOptionModel(questionTypeOptionEntities.get(i)));
        }
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.questionTypeOptionsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findAll();

        Assert.assertNotNull(questionTypeOptionModels);
        Assert.assertEquals(0, questionTypeOptionModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = this.createQuestionTypeOptionEntityMock(id, 1, 10, 20, 60, 1);
        given(this.questionTypeOptionsMapper.find(id)).willReturn(questionTypeOptionEntity);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.questionTypeOptionsService.find(id);

        Assert.assertNotNull(questionTypeOptionModel);
        Assert.assertEquals(this.mapQuestionTypeOptionModel(questionTypeOptionEntity), questionTypeOptionModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.questionTypeOptionsMapper.find(id)).willReturn(null);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.questionTypeOptionsService.find(id);

        Assert.assertNull(questionTypeOptionModel);
    }

    @Test
    public void testFindByIssueStandardPresentList() throws Exception {
        final Integer issuestandardId = 5;
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = new ArrayList<>(2);

        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(1, 1, 1, 10, null, issuestandardId));
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(2, 2, 10, 20, 60, issuestandardId));
        given(this.questionTypeOptionsMapper.findByIssueStandardId(issuestandardId))
                .willReturn(questionTypeOptionEntities);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issuestandardId);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findByIssueStandard(issueStandardModel);

        Assert.assertNotNull(questionTypeOptionModels);
        Assert.assertEquals(questionTypeOptionEntities.size(), questionTypeOptionModels.size());
        for (int i = 0; i < questionTypeOptionModels.size(); i++) {
            Assert.assertEquals(this.mapQuestionTypeOptionModel(questionTypeOptionEntities.get(i)),
                    questionTypeOptionModels.get(i));
        }
    }

    @Test
    public void testFindByIssueStandardAbsentList() throws Exception {
        final Integer issueStandardId = 10;
        given(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandardId)).willReturn(new ArrayList<>(0));

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findByIssueStandard(issueStandardModel);
        Assert.assertNotNull(questionTypeOptionModels);
        Assert.assertEquals(0, questionTypeOptionModels.size());
    }

    @Test
    public  void saveUpdatesEntity() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(1, 1, 10, 20, 60, 1);
        this.questionTypeOptionsService.save(questionTypeOptionModel);

        verify(this.questionTypeOptionsMapper, times(1))
                .update(this.mapQuestionTypeOptionEntity(questionTypeOptionModel));
    }

    @Test
    public void saveInsertsEntity() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(null, 1, 10, 20, 60, 1);
        final Integer id = 10;
        doAnswer(invocations -> {
            final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                    = (IssueStandardQuestionTypeOptionEntity) invocations.getArguments()[0];
            questionTypeOptionEntity.setId(id);
            return null;
        }).when(this.questionTypeOptionsMapper).insert(Mockito.any(IssueStandardQuestionTypeOptionEntity.class));

        this.questionTypeOptionsService.save(questionTypeOptionModel);

        verify(this.questionTypeOptionsMapper, times(1))
                .insert(this.mapQuestionTypeOptionEntity(questionTypeOptionModel));
        Assert.assertEquals(id, questionTypeOptionModel.getId());
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(1, 1, 10, 20, 60, 1);

        this.questionTypeOptionsService.delete(questionTypeOptionModel);
        verify(this.questionTypeOptionsMapper, times(1))
                .delete(this.mapQuestionTypeOptionEntity(questionTypeOptionModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(null, 1, 10, 20, 60, 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.questionTypeOptionsService.delete(questionTypeOptionModel);
    }
}
