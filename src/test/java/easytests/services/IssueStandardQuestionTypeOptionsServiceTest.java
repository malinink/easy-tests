package easytests.services;

import easytests.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.mappers.IssueStandardQuestionTypeOptionsMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModel;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.models.QuestionTypeModelInterface;
import easytests.options.IssueStandardQuestionTypeOptionsOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
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

        final QuestionTypeModelInterface questionTypeModel = Mockito.mock(QuestionTypeModelInterface.class);
        Mockito.when(questionTypeModel.getId()).thenReturn(questionTypeId);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        questionTypeOptionModel.setId(id);
        questionTypeOptionModel.setQuestionType(questionTypeModel);
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

    private List<IssueStandardQuestionTypeOptionEntity> getQuestionTypeOptionEntities() {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = new ArrayList<>(2);
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(1, 2, 10, 20, null, 3));
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(2, 3, 5, 25, 60, 2));
        return questionTypeOptionEntities;
    }

    private List<IssueStandardQuestionTypeOptionModelInterface> getQuestionTypeOptionModels() {
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);
        for (IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity: this.getQuestionTypeOptionEntities()) {
            questionTypeOptionModels.add(this.mapQuestionTypeOptionModel(questionTypeOptionEntity));
        }
        return questionTypeOptionModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities
                = this.getQuestionTypeOptionEntities();

        given(this.questionTypeOptionsMapper.findAll()).willReturn(questionTypeOptionEntities);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findAll();

        Assert.assertNotNull(questionTypeOptionModels);
        Assert.assertEquals(this.getQuestionTypeOptionModels(), questionTypeOptionModels);
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
    public void testFindAllWithOptions() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities
                = this.getQuestionTypeOptionEntities();
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.getQuestionTypeOptionModels();

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);
        given(this.questionTypeOptionsMapper.findAll()).willReturn(questionTypeOptionEntities);
        given(questionTypeOptionsOptions.withRelations(Mockito.anyList())).willReturn(questionTypeOptionModels);

        final List<IssueStandardQuestionTypeOptionModelInterface> foundedQuestionTypeOptionModels
                = this.questionTypeOptionsService.findAll(questionTypeOptionsOptions);

        verify(questionTypeOptionsOptions).withRelations(questionTypeOptionModels);
        Assert.assertNotNull(foundedQuestionTypeOptionModels);
        Assert.assertEquals(questionTypeOptionModels, foundedQuestionTypeOptionModels);
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
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = this.createQuestionTypeOptionEntityMock(id, 1, 10, 20, 60, 1);
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.mapQuestionTypeOptionModel(questionTypeOptionEntity);

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);
        given(this.questionTypeOptionsMapper.find(id)).willReturn(questionTypeOptionEntity);
        given(questionTypeOptionsOptions.withRelations(questionTypeOptionModel)).willReturn(questionTypeOptionModel);

        final IssueStandardQuestionTypeOptionModelInterface foundedQuestionTypeOptionModel
                = this.questionTypeOptionsService.find(id, questionTypeOptionsOptions);

        verify(questionTypeOptionsOptions).withRelations(questionTypeOptionModel);
        Assert.assertNotNull(foundedQuestionTypeOptionModel);
        Assert.assertEquals(questionTypeOptionModel, foundedQuestionTypeOptionModel);
    }

    @Test
    public void testFindByIssueStandardPresentList() throws Exception {
        final Integer issueStandardId = 5;
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = new ArrayList<>(2);

        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(1, 1, 1, 10, null, issueStandardId));
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(2, 2, 10, 20, 60, issueStandardId));
        given(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandardId))
                .willReturn(questionTypeOptionEntities);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

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
    public void testFindByIssueStandardWithOptions() throws Exception {
        final Integer issueStandardId = 5;
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = new ArrayList<>(2);
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(1, 1, 1, 10, null, issueStandardId));
        questionTypeOptionEntities.add(this.createQuestionTypeOptionEntityMock(2, 2, 10, 20, 60, issueStandardId));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);
        questionTypeOptionModels.add(this.mapQuestionTypeOptionModel(questionTypeOptionEntities.get(0)));
        questionTypeOptionModels.add(this.mapQuestionTypeOptionModel(questionTypeOptionEntities.get(1)));

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);
        given(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandardId))
                .willReturn(questionTypeOptionEntities);
        given(questionTypeOptionsOptions.withRelations(questionTypeOptionModels)).willReturn(questionTypeOptionModels);

        List<IssueStandardQuestionTypeOptionModelInterface> foundedQuestionTypeOptionModels
                = this.questionTypeOptionsService.findByIssueStandard(
                        questionTypeOptionModels.get(0).getIssueStandard(), questionTypeOptionsOptions);

        verify(questionTypeOptionsOptions).withRelations(questionTypeOptionModels);
        Assert.assertNotNull(foundedQuestionTypeOptionModels);
        Assert.assertEquals(questionTypeOptionModels, foundedQuestionTypeOptionModels);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(1, 1, 10, 20, 60, 1);
        this.questionTypeOptionsService.save(questionTypeOptionModel);

        verify(this.questionTypeOptionsMapper, times(1))
                .update(this.mapQuestionTypeOptionEntity(questionTypeOptionModel));
    }

    @Test
    public void testSaveInsertsEntity() throws Exception {
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
    public void testSaveList() throws Exception {
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);
        questionTypeOptionModels.add(this.createQuestionTypeOptionModel(1, 1, 10, 20, 60, 1));
        questionTypeOptionModels.add(this.createQuestionTypeOptionModel(null, 1, 10, 20, 60, 1));

        final Integer id = 10;
        doAnswer(invocations -> {
            final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                    = (IssueStandardQuestionTypeOptionEntity) invocations.getArguments()[0];
            questionTypeOptionEntity.setId(id);
            return null;
        }).when(this.questionTypeOptionsMapper).insert(Mockito.any(IssueStandardQuestionTypeOptionEntity.class));

        this.questionTypeOptionsService.save(questionTypeOptionModels);

        verify(this.questionTypeOptionsMapper, times(1))
                .update(this.mapQuestionTypeOptionEntity(questionTypeOptionModels.get(0)));
        verify(this.questionTypeOptionsMapper, times(1))
                .insert(this.mapQuestionTypeOptionEntity(questionTypeOptionModels.get(1)));
        Assert.assertEquals(id, questionTypeOptionModels.get(1).getId());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(1, 2, 10, 20, 60, 2);

        this.questionTypeOptionsService.save(questionTypeOptionModel, questionTypeOptionsOptions);

        verify(questionTypeOptionsOptions).saveWithRelations(questionTypeOptionModel);
    }

    @Test
    public void testSaveListWithOptions() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.getQuestionTypeOptionModels();

        this.questionTypeOptionsService.save(questionTypeOptionModels, questionTypeOptionsOptions);

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            verify(questionTypeOptionsOptions).saveWithRelations(questionTypeOptionModel);
        }
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

        verify(this.questionTypeOptionsMapper, times(0))
                .delete(this.mapQuestionTypeOptionEntity(questionTypeOptionModel));
    }

    @Test
    public void testDeleteList() throws Exception {
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.getQuestionTypeOptionModels();

        this.questionTypeOptionsService.delete(questionTypeOptionModels);

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            verify(this.questionTypeOptionsMapper, times(1))
                    .delete(this.mapQuestionTypeOptionEntity(questionTypeOptionModel));
        }
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.createQuestionTypeOptionModel(1, 1, 10, 20, 60, 1);

        this.questionTypeOptionsService.delete(questionTypeOptionModel, questionTypeOptionsOptions);

        verify(questionTypeOptionsOptions).deleteWithRelations(questionTypeOptionModel);
    }

    @Test
    public void testDeleteListWithOptions() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.getQuestionTypeOptionModels();

        this.questionTypeOptionsService.delete(questionTypeOptionModels, questionTypeOptionsOptions);

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            verify(questionTypeOptionsOptions).deleteWithRelations(questionTypeOptionModel);
        }
    }
}
