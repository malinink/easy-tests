package easytests.core.options;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import easytests.support.SubjectsSupport;
import easytests.support.TopicsSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Yarik2308
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsOptionsTest {

    private TopicsSupport topicsSupport = new TopicsSupport();

    private TopicsOptionsInterface topicsOptions;

    private SubjectsOptionsInterface subjectsOptions;

    private QuestionsOptionsInterface questionsOptions;

    private TopicModelInterface topicModel;

    private List<TopicModelInterface> topicsModels;

    private SubjectModelInterface subjectModel;

    private List<SubjectModelInterface> subjectsModels;

    private SubjectModelInterface subjectModelWithId;

    private List<QuestionModelInterface> questionsModels;

    private List<List<QuestionModelInterface>> questionsModelsList;

    private SubjectsServiceInterface subjectsService;

    private QuestionsServiceInterface questionsService;

    private TopicsServiceInterface topicsService;


    private ArgumentCaptor<List> listCaptor;

    @Before
    public void before(){
        this.topicModel = Mockito.mock(TopicModelInterface.class);
        this.subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        this.questionsService = Mockito.mock(QuestionsServiceInterface.class);
        this.topicsService = Mockito.mock(TopicsServiceInterface.class);
        this.subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        this.questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.topicsOptions = new TopicsOptions();
        this.topicsOptions.setSubjectsService(subjectsService);
        this.topicsOptions.setQuestionsService(questionsService);
        this.topicsOptions.setTopicsService(topicsService);

        this.listCaptor = ArgumentCaptor.forClass(List.class);
    }

    private TopicsOptionsTest withTopicModel() {
        this.topicModel = this.topicsSupport.getModelFixtureMock(0);
        return this;
    }

    private TopicsOptionsTest withSubjectModelFounded() {
        this.subjectModel = Mockito.mock(SubjectModelInterface.class);
        this.subjectModelWithId = Mockito.mock(SubjectModelInterface.class);

        given(this.topicModel.getSubject()).willReturn(subjectModelWithId);
        given(this.subjectsService.find(topicModel.getSubject().getId(), this.subjectsOptions)).willReturn(subjectModel);

        return this;
    }

    private TopicsOptionsTest withSubjectModelInjected() {
        given(this.topicModel.getSubject()).willReturn(this.subjectModel);
        return this;
    }

    private TopicsOptionsTest withSubjectsModelsFounded() {

        this.subjectsModels = new ArrayList<>(2);
        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelWithIdFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelWithIdSecond = Mockito.mock(SubjectModelInterface.class);

        given(this.topicsModels.get(0).getSubject()).willReturn(subjectModelWithIdFirst);
        given(this.topicsModels.get(1).getSubject()).willReturn(subjectModelWithIdSecond);
        given(this.topicsModels.get(0).getSubject().getId()).willReturn(0);
        given(this.topicsModels.get(1).getSubject().getId()).willReturn(1);

        this.subjectsModels.add(subjectModelFirst);
        this.subjectsModels.add(subjectModelSecond);

        given(subjectsService.find(this.topicsModels.get(0).getSubject().getId(), subjectsOptions))
                .willReturn(subjectsModels.get(0));
        given(subjectsService.find(this.topicsModels.get(1).getSubject().getId(), subjectsOptions))
                .willReturn(subjectsModels.get(1));

        return this;
    }

    private TopicsOptionsTest withQustionsModelsFounded() {
        this.questionsModels = new ArrayList<>();

        given(this.questionsService.findByTopic(this.topicModel, this.questionsOptions)).willReturn(this.questionsModels);

        return this;
    }

    private TopicsOptionsTest withQustionsModelsInjected() {
        this.topicModel.setQuestions(this.questionsModels);
        given(this.topicModel.getQuestions()).willReturn(this.questionsModels);
        return this;
    }

    private TopicsOptionsTest withSubject() {
        this.topicsOptions.withSubject(this.subjectsOptions);
        return this;
    }

    private TopicsOptionsTest withQuestions() {
        this.topicsOptions.withQuestions(this.questionsOptions);
        return this;
    }

    private TopicsOptionsTest withTopicsList() {
        this.topicsModels = new ArrayList<>(2);
        this.topicsModels.add(this.topicsSupport.getModelFixtureMock(0));
        this.topicsModels.add(this.topicsSupport.getModelFixtureMock(1));

        return this;
    }

    private TopicsOptionsTest withQuestionsModelsListFounded() {
        this.questionsModelsList = new ArrayList<>(2);
        this.questionsModelsList.add(new ArrayList<>());
        this.questionsModelsList.add(new ArrayList<>());

        given(this.questionsService.findByTopic(this.topicsModels.get(0), this.questionsOptions))
                .willReturn(questionsModelsList.get(0));
        given(this.questionsService.findByTopic(this.topicsModels.get(1), this.questionsOptions))
                .willReturn(questionsModelsList.get(1));

        return this;
    }

    @Test
    public void testWithRelationsSubjectOnSingleModel() throws Exception {

        this.withTopicModel().withSubjectModelFounded().withSubject();

        final TopicModelInterface topicModelWithRelations =  this.topicsOptions.withRelations(this.topicModel);

        Assert.assertSame(this.topicModel, topicModelWithRelations);
        subjectsService.find(1, this.subjectsOptions);
        verify(this.topicModel).setSubject(this.subjectModel);
    }

    @Test
    public void testWithRelationsQuestionsOnSingleModel() throws Exception {

        this.withTopicModel().withQustionsModelsFounded().withQuestions();

        final TopicModelInterface topicModelWithRelations =  this.topicsOptions.withRelations(this.topicModel);

        Assert.assertSame(this.topicModel,topicModelWithRelations);
        verify(this.questionsService).findByTopic(this.topicModel, this.questionsOptions);
        verify(this.topicModel).setQuestions(this.questionsModels);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {
        final TopicModelInterface nullTopicModel = null;

        final TopicModelInterface topicModelWithRelations =  this.topicsOptions.withRelations(nullTopicModel);

        Assert.assertSame(null, topicModelWithRelations);
    }

    @Test
    public void testWithRelationsSubjectOnList() throws Exception{

        this.withTopicsList().withSubjectsModelsFounded().withSubject();

        final List<TopicModelInterface> topicsModelsWithRelations =  this.topicsOptions.withRelations(this.topicsModels);

        Assert.assertSame(topicsModelsWithRelations, this.topicsModels);
        this.subjectsService.find(0, this.subjectsOptions);
        this.subjectsService.find(1, this.subjectsOptions);
        verify(this.topicsModels.get(0)).setSubject(this.subjectsModels.get(0));
        verify(this.topicsModels.get(1)).setSubject(this.subjectsModels.get(1));
    }


    @Test
    public void testWithRelationsQuestionsOnList() throws Exception{
        this.withTopicsList().withQuestionsModelsListFounded().withQuestions();

        final List<TopicModelInterface> topicsModelsWithRelations =  topicsOptions.withRelations(topicsModels);

        Assert.assertSame(topicsModelsWithRelations,topicsModels);
        verify(this.questionsService).findByTopic(this.topicsModels.get(0), this.questionsOptions);
        verify(this.topicsModels.get(0)).setQuestions(this.questionsModelsList.get(0));
        verify(this.questionsService).findByTopic(this.topicsModels.get(1), this.questionsOptions);
        verify(this.topicsModels.get(1)).setQuestions(this.questionsModelsList.get(1));
    }

    @Test
    public void testSaveWithRelationsSubject() throws Exception {
        this.withTopicModel().withSubjectModelInjected().withSubject();
        final InOrder inOrder = Mockito.inOrder(subjectsService, topicsService);

        this.topicsOptions.saveWithRelations(this.topicModel);

        inOrder.verify(this.subjectsService).save(this.topicModel.getSubject(), this.subjectsOptions);
        inOrder.verify(this.topicsService).save(this.topicModel);
    }

    @Test
    public void testSaveWithRelationsQuestions() throws Exception{

        this.withTopicModel().withQustionsModelsInjected().withQuestions();

        final InOrder inOrder = Mockito.inOrder(this.topicsService, this.questionsService);

        this.topicsOptions.saveWithRelations(this.topicModel);

        inOrder.verify(this.topicsService).save(this.topicModel);
        inOrder.verify(this.questionsService).save(this.topicModel.getQuestions(), this.questionsOptions);
    }

    @Test
    public void testDeleteWithRelationsSubject() throws Exception{

        this.withTopicModel().withSubjectModelInjected().withSubject();

        final InOrder inOrder = Mockito.inOrder(this.topicsService, this.subjectsService);

        this.topicsOptions.deleteWithRelations(this.topicModel);

        inOrder.verify(this.topicsService).delete(this.topicModel);
        inOrder.verify(this.subjectsService).save(this.topicModel.getSubject(), this.subjectsOptions);
    }

    @Test
    public void testDeleteWithRelationsQuestion() throws Exception{

        this.withTopicModel().withQustionsModelsInjected().withQuestions();
        
        final InOrder inOrder = Mockito.inOrder(this.questionsService, this.topicsService);

        this.topicsOptions.deleteWithRelations(this.topicModel);

        inOrder.verify(this.questionsService).save(this.topicModel.getQuestions(), this.questionsOptions);
        inOrder.verify(this.topicsService).delete(this.topicModel);
    }

    @Test
    public void testWithSubject() throws Exception {
        final TopicsOptionsInterface topicsOptionsWithSubject = this.topicsOptions.withSubject(this.subjectsOptions);
        Assert.assertSame(topicsOptionsWithSubject, this.topicsOptions);
    }

    @Test
    public void testWithQuestions() throws Exception {
        final TopicsOptionsInterface topicsOptionsWithQuestions = this.topicsOptions.withQuestions(this.questionsOptions);
        Assert.assertEquals(topicsOptionsWithQuestions, this.topicsOptions);
    }

    @Test
    public void testSaveDeleteWithRelationsSubject() {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final SubjectModelInterface subjectModelId = Mockito.mock(SubjectModelInterface.class);
        given(topicModel.getSubject()).willReturn(subjectModelId);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        topicsOptions.withSubject(subjectsOptions);

        final SubjectModelInterface subjectModel = new SubjectModel();
        topicModel.setSubject(subjectModel);

        topicsOptions.saveWithRelations(topicModel);
        verify(topicsService).save(topicModel);

        topicsOptions.deleteWithRelations(topicModel);
        verify(topicsService).delete(topicModel);

        verify(subjectsService, times(2)).save(topicModel.getSubject(), subjectsOptions);
    }

    @Test
    public void testSaveDeleteWithRelationsQuestion() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setTopicsService(topicsService);

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        topicsOptions.withQuestions(questionsOptions);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        topicModel.setQuestions(questionsModels);

        topicsOptions.saveWithRelations((topicModel));
        verify(topicsService).save(topicModel);

        topicsOptions.deleteWithRelations(topicModel);
        verify(topicsService).delete(topicModel);
        
        verify(questionsService, times(2)).save(topicModel.getQuestions(), questionsOptions);
    }

}
