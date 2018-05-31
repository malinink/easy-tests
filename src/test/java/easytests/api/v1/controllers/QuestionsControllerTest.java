package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuestionsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.entities.QuestionEntity;
import easytests.core.models.*;
import easytests.core.models.empty.TopicModelEmpty;
import easytests.core.options.*;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.options.builder.TopicsOptionsBuilder;
import easytests.core.services.*;
import easytests.support.AnswersSupport;
import easytests.support.JsonSupport;
import easytests.support.QuestionsSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author RisaMagpie
 */
@Import({QuestionsMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = QuestionsController.class, secure = false)
public class QuestionsControllerTest {
    private static String id = "id";
    private static String text = "text";
    private static String type = "type";
    private static String topic = "topic";
    private static String answers = "answers";
    private static String isRight = "isRight";
    private static String number = "number";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionsService questionsService;

    @MockBean
    private TopicsService topicsService;

    @MockBean
    private AnswersService answersService;

    @MockBean
    private QuestionTypesService questionTypesService;

    @Autowired
    @Qualifier("QuestionsMapperV1")
    private QuestionsMapper questionsMapper;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @MockBean
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @MockBean
    private QuestionsOptions questionsOptions;

    @MockBean
    private AnswersOptions answersOptions;

    private QuestionsSupport questionSupport = new QuestionsSupport();

    private AnswersSupport answersSupport = new AnswersSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<QuestionModelInterface> questionsModels = new ArrayList<>();

        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(answerIdx -> {
            final AnswerModel answerModel = new AnswerModel();
            answerModel.map(answersSupport.getEntityFixtureMock(answerIdx));
            answersModels.add(answerModel);
        });

        IntStream.range(0, 2).forEach(idx -> {
            final QuestionModel questionModel = new QuestionModel();
            questionModel.map(this.questionSupport.getEntityFixtureMock(idx));
            questionModel.setAnswers(answersModels);
            questionsModels.add(questionModel);
        });

        int topicIdParamValue = 1;

        final TopicModel topicModel = new TopicModel();
        topicModel.setId(topicIdParamValue);
        when(this.topicsService.find(topicIdParamValue, this.topicsOptionsBuilder.forAuth()))
                .thenReturn(topicModel);
        when(this.questionsService.findByTopic(any(), any())).thenReturn(questionsModels);
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);

        this.mvc.perform(get("/v1/questions?topicId={topicIdParamValue}", topicIdParamValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, questionsModels.get(0).getId())
                                .with(text, questionsModels.get(0).getText())
                                .with(type, questionsModels.get(0).getQuestionType().getId())
                                .with(topic, new JsonSupport().with(id, questionsModels.get(0).getTopic().getId()))
                                .with(answers, new JsonSupport()
                                        .with(new JsonSupport()
                                                .with(id, questionsModels.get(0).getAnswers().get(0).getId())
                                                .with(text, questionsModels.get(0).getAnswers().get(0).getTxt())
                                                .with(isRight, questionsModels.get(0).getAnswers().get(0).getRight())
                                                .with(number, questionsModels.get(0).getAnswers().get(0).getSerialNumber()))
                                        .with(new JsonSupport()
                                                .with(id, questionsModels.get(0).getAnswers().get(1).getId())
                                                .with(text, questionsModels.get(0).getAnswers().get(1).getTxt())
                                                .with(isRight, questionsModels.get(0).getAnswers().get(1).getRight())
                                                .with(number, questionsModels.get(0).getAnswers().get(1).getSerialNumber()))
                                )
                        )
                        .with(new JsonSupport()
                                .with(id, questionsModels.get(1).getId())
                                .with(text, questionsModels.get(1).getText())
                                .with(type, questionsModels.get(1).getQuestionType().getId())
                                .with(topic, new JsonSupport().with(id, questionsModels.get(1).getTopic().getId()))
                                .with(answers, new JsonSupport()
                                        .with(new JsonSupport()
                                                .with(id, questionsModels.get(1).getAnswers().get(0).getId())
                                                .with(text, questionsModels.get(1).getAnswers().get(0).getTxt())
                                                .with(isRight, questionsModels.get(1).getAnswers().get(0).getRight())
                                                .with(number, questionsModels.get(1).getAnswers().get(0).getSerialNumber()))
                                        .with(new JsonSupport()
                                                .with(id, questionsModels.get(1).getAnswers().get(1).getId())
                                                .with(text, questionsModels.get(1).getAnswers().get(1).getTxt())
                                                .with(isRight, questionsModels.get(1).getAnswers().get(1).getRight())
                                                .with(number, questionsModels.get(1).getAnswers().get(1).getSerialNumber()))
                                )
                        ).build()
                ))
                .andReturn();
    }

    @Test
    public void testListNotFound() throws Exception {
        int topicIdParamValue = 5;

        when(this.topicsService.find(topicIdParamValue))
                .thenReturn(null);

        this.mvc.perform(get("/v1/questions?topicId={topicIdParamValue}", topicIdParamValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {
        int topicIdParamValue = 1;

        when(this.topicsService.find(topicIdParamValue, this.topicsOptionsBuilder.forAuth()))
                .thenReturn(new TopicModelEmpty(topicIdParamValue));

        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/questions?topicId={topicIdParamValue}", topicIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();

    }

    @Test
    public void testCreateSuccess() throws Exception {
        final List<AnswerModelInterface> answersList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final AnswerModelInterface answer = new AnswerModel();
            answer.map(this.answersSupport.getEntityAdditionalMock(i));
            answer.setQuestion(null);
            answersList.add(answer);
        }
        final QuestionEntity questionEntity = this.questionSupport.getEntityAdditionalMock(0);
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(questionEntity);
        questionModel.setAnswers(answersList);
        questionModel.setId(5);
        doAnswer(invocation -> {
            final QuestionModel questionModel1 = (QuestionModel) invocation.getArguments()[0];
            questionModel1.setId(5);
            return null;
        }).when(this.questionsService).save(any(QuestionModelInterface.class), any(QuestionsOptionsInterface.class));
        final ArgumentCaptor<QuestionModelInterface>
                questionModelCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);
        when(this.topicsService.find(any(), any())).thenReturn(questionModel.getTopic());
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);
        mvc.perform(post("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(text, questionModel.getText())
                        .with(type, questionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, questionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(text, answersList.get(0).getTxt())
                                        .with(isRight, answersList.get(0).getRight())
                                        .with(number, answersList.get(0).getSerialNumber())
                                )
                                .with(new JsonSupport()
                                        .with(text, answersList.get(1).getTxt())
                                        .with(isRight, answersList.get(1).getRight())
                                        .with(number, answersList.get(1).getSerialNumber())
                                )
                                .with(new JsonSupport()
                                        .with(text, answersList.get(2).getTxt())
                                        .with(isRight, answersList.get(2).getRight())
                                        .with(number, answersList.get(2).getSerialNumber())
                                ))
                        .build()
                ))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        new JsonSupport()
                                .with(id, 5)
                                .build()
                ))
                .andReturn();
        verify(this.questionsService, times(1))
                .save(questionModelCaptor.capture(), any(QuestionsOptionsInterface.class));
        Assert.assertEquals(questionModel.getId(), questionModelCaptor.getValue().getId());
        Assert.assertEquals(questionModel.getText(), questionModelCaptor.getValue().getText());
        Assert.assertEquals(questionModel.getTopic().getId(), questionModelCaptor.getValue().getTopic().getId());
        this.answersSupport.assertEqualsWithoutQuestion(questionModel.getAnswers().get(0),
                questionModelCaptor.getValue().getAnswers().get(0));
    }

    @Test
    public void testCreateWithIdFailed() throws Exception {
        when(this.topicsService.find(any(), any())).thenReturn(new TopicModelEmpty());
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);
        mvc.perform(post("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 2)
                        .with(text, "Questiontext")
                        .with(type, 1)
                        .with(topic, new JsonSupport().with(id, 1))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(text, "Answer1text")
                                        .with(isRight, false)
                                        .with(number, 1)))
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testCreateWithAnswersEmptyFailed() throws Exception {
        mvc.perform(post("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(text, "Questiontext")
                        .with(type, 1)
                        .with(topic, new JsonSupport().with(id, 1))
                        .with(answers, new JsonSupport())
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testCreateBadRequest() throws Exception {
        when(this.topicsService.find(any(), any())).thenReturn(new TopicModelEmpty());
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(false);
        mvc.perform(post("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(text, "Questiontext")
                        .with(type, 1)
                        .with(topic, new JsonSupport().with(id, 1))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(text, "Answer1text")
                                        .with(isRight, false)
                                        .with(number, 1)))
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testUpdateSuccess() throws Exception {
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(answerIdx ->{
            final AnswerModel answerModel = new AnswerModel();
            answerModel.map(answersSupport.getEntityFixtureMock(answerIdx));
            answersModels.add(answerModel);
        });

        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(this.questionSupport.getEntityFixtureMock(0));

        final QuestionModelInterface newQuestionModel = new QuestionModel();
        newQuestionModel.map(this.questionSupport.getEntityAdditionalMock(1));

        questionModel.setAnswers(answersModels);

        final List<AnswerModelInterface> newAnswersModels = new ArrayList<>();
        final AnswerModel newAnswerModelAdditional = new AnswerModel();
        newAnswerModelAdditional.map(answersSupport.getEntityAdditionalMock(2));
        newAnswersModels.add(newAnswerModelAdditional);
        newAnswersModels.add(answersModels.get(1));
        newQuestionModel.setAnswers(newAnswersModels);

        final QuestionsOptionsInterface questionOptionWithAnswers =
                new QuestionsOptions().withAnswers(new AnswersOptions());

        when(this.questionsService.find(any(), any())).thenReturn(questionModel);
        when(this.topicsService.find(any(), any())).thenReturn(new TopicModel());
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(true);

        final ArgumentCaptor<QuestionModelInterface> questionModelCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);

        mvc.perform(put("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, newQuestionModel.getId())
                        .with(text, newQuestionModel.getText())
                        .with(type, newQuestionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, newQuestionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(0).getId())
                                        .with(text, newQuestionModel.getAnswers().get(0).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(0).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(0).getSerialNumber()))
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(1).getId())
                                        .with(text, newQuestionModel.getAnswers().get(1).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(1).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(1).getSerialNumber()))
                        )
                        .build()
                ))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
        verify(this.questionsService, times(1)).save(questionModelCaptor.capture(), eq(questionOptionWithAnswers));
        this.questionSupport.assertEquals(questionModel, questionModelCaptor.getValue());
    }

    @Test
    public void testUpdateWithoutIdFailed() throws Exception {
        final QuestionModelInterface newQuestionModel = new QuestionModel();
        newQuestionModel.map(this.questionSupport.getEntityAdditionalMock(1));

        final List<AnswerModelInterface> newAnswersModels = new ArrayList<>();
        final AnswerModel newAnswerModelAdditional = new AnswerModel();
        newAnswerModelAdditional.map(answersSupport.getEntityAdditionalMock(2));
        newAnswersModels.add(newAnswerModelAdditional);
        newAnswerModelAdditional.map(answersSupport.getEntityFixtureMock(1));
        newAnswersModels.add(newAnswerModelAdditional);
        newQuestionModel.setAnswers(newAnswersModels);

        mvc.perform(put("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(text, newQuestionModel.getText())
                        .with(type, newQuestionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, newQuestionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(0).getId())
                                        .with(text, newQuestionModel.getAnswers().get(0).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(0).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(0).getSerialNumber()))
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(1).getId())
                                        .with(text, newQuestionModel.getAnswers().get(1).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(1).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(1).getSerialNumber()))
                        )
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        when(this.questionsService.find(any(Integer.class))).thenReturn(null);


        final QuestionModelInterface newQuestionModel = new QuestionModel();
        newQuestionModel.map(this.questionSupport.getEntityAdditionalMock(1));

        final List<AnswerModelInterface> newAnswersModels = new ArrayList<>();
        final AnswerModel newAnswerModelAdditional = new AnswerModel();
        newAnswerModelAdditional.map(answersSupport.getEntityAdditionalMock(2));
        newAnswersModels.add(newAnswerModelAdditional);
        newAnswerModelAdditional.map(answersSupport.getEntityFixtureMock(1));
        newAnswersModels.add(newAnswerModelAdditional);
        newQuestionModel.setAnswers(newAnswersModels);

        mvc.perform(put("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, newQuestionModel.getId())
                        .with(text, newQuestionModel.getText())
                        .with(type, newQuestionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, newQuestionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(0).getId())
                                        .with(text, newQuestionModel.getAnswers().get(0).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(0).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(0).getSerialNumber()))
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(1).getId())
                                        .with(text, newQuestionModel.getAnswers().get(1).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(1).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(1).getSerialNumber()))
                        )
                        .build()
                ))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testUpdateBadTopicId() throws Exception {
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(answerIdx ->{
            final AnswerModel answerModel = new AnswerModel();
            answerModel.map(answersSupport.getEntityFixtureMock(answerIdx));
            answersModels.add(answerModel);
        });

        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(this.questionSupport.getEntityFixtureMock(0));

        final QuestionModelInterface newQuestionModel = new QuestionModel();
        newQuestionModel.map(this.questionSupport.getEntityAdditionalMock(1));

        questionModel.setAnswers(answersModels);

        final List<AnswerModelInterface> newAnswersModels = new ArrayList<>();
        final AnswerModel newAnswerModelAdditional = new AnswerModel();
        newAnswerModelAdditional.map(answersSupport.getEntityAdditionalMock(2));
        newAnswersModels.add(newAnswerModelAdditional);
        newAnswersModels.add(answersModels.get(1));
        newQuestionModel.setAnswers(newAnswersModels);


        when(this.questionsService.find(any(), any())).thenReturn(questionModel);
        when(this.topicsService.find(any(), any())).thenReturn(new TopicModel());
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(false);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(true);

        mvc.perform(put("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, newQuestionModel.getId())
                        .with(text, newQuestionModel.getText())
                        .with(type, newQuestionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, newQuestionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(0).getId())
                                        .with(text, newQuestionModel.getAnswers().get(0).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(0).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(0).getSerialNumber()))
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(1).getId())
                                        .with(text, newQuestionModel.getAnswers().get(1).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(1).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(1).getSerialNumber()))
                        )
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testUpdateForbidden() throws Exception {
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(answerIdx ->{
            final AnswerModel answerModel = new AnswerModel();
            answerModel.map(answersSupport.getEntityFixtureMock(answerIdx));
            answersModels.add(answerModel);
        });

        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(this.questionSupport.getEntityFixtureMock(0));

        final QuestionModelInterface newQuestionModel = new QuestionModel();
        newQuestionModel.map(this.questionSupport.getEntityAdditionalMock(1));

        questionModel.setAnswers(answersModels);

        final List<AnswerModelInterface> newAnswersModels = new ArrayList<>();
        final AnswerModel newAnswerModelAdditional = new AnswerModel();
        newAnswerModelAdditional.map(answersSupport.getEntityAdditionalMock(2));
        newAnswersModels.add(newAnswerModelAdditional);
        newAnswersModels.add(answersModels.get(1));
        newQuestionModel.setAnswers(newAnswersModels);


        when(this.questionsService.find(any(), any())).thenReturn(questionModel);
        when(this.topicsService.find(any(), any())).thenReturn(new TopicModel());
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(false);

        mvc.perform(put("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, newQuestionModel.getId())
                        .with(text, newQuestionModel.getText())
                        .with(type, newQuestionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, newQuestionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(0).getId())
                                        .with(text, newQuestionModel.getAnswers().get(0).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(0).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(0).getSerialNumber()))
                                .with(new JsonSupport()
                                        .with(id, newQuestionModel.getAnswers().get(1).getId())
                                        .with(text, newQuestionModel.getAnswers().get(1).getTxt())
                                        .with(isRight, newQuestionModel.getAnswers().get(1).getRight())
                                        .with(number, newQuestionModel.getAnswers().get(1).getSerialNumber()))
                        )
                        .build()
                ))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowSuccess() throws Exception {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(this.questionSupport.getEntityFixtureMock(0));
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final AnswerModel answerModel = new AnswerModel();
            answerModel.map(this.answersSupport.getEntityFixtureMock(idx));
            answersModels.add(answerModel);
        });
        questionModel.setAnswers(answersModels);
        when(this.questionsOptionsBuilder.forAuth())
                .thenReturn(new QuestionsOptions().withAnswers(new AnswersOptions()));
        when(this.questionsService.find(any(Integer.class), any(QuestionsOptionsInterface.class)))
                .thenReturn(questionModel);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(true);

        mvc.perform(get("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(id, questionModel.getId())
                        .with(text, questionModel.getText())
                        .with(type, questionModel.getQuestionType().getId())
                        .with(topic, new JsonSupport().with(id, questionModel.getTopic().getId()))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(id, questionModel.getAnswers().get(0).getId())
                                        .with(text, questionModel.getAnswers().get(0).getTxt())
                                        .with(isRight, questionModel.getAnswers().get(0).getRight())
                                        .with(number, questionModel.getAnswers().get(0).getSerialNumber())
                                )
                                .with(new JsonSupport()
                                        .with(id, questionModel.getAnswers().get(1).getId())
                                        .with(text, questionModel.getAnswers().get(1).getTxt())
                                        .with(isRight, questionModel.getAnswers().get(1).getRight())
                                        .with(number, questionModel.getAnswers().get(1).getSerialNumber())
                                )
                        )
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testShowNotFound() throws Exception {
        when(this.questionsOptionsBuilder.forAuth()).thenReturn(new QuestionsOptions());
        when(this.questionsService.find(any(Integer.class), any(QuestionsOptionsInterface.class)))
                .thenReturn(null);

        mvc.perform(get("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowForbidden() throws Exception {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(this.questionSupport.getEntityFixtureMock(0));
        when(this.questionsOptionsBuilder.forAuth()).thenReturn(new QuestionsOptions());
        when(this.questionsService.find(any(Integer.class), any(QuestionsOptionsInterface.class)))
                .thenReturn(questionModel);
        when(this.acl.hasAccess(any(UserModelInterface.class))).thenReturn(false);

        mvc.perform(get("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        final QuestionModelInterface questionModelForAuth = this.questionSupport.getModelFixtureMock(0);
        final QuestionModelInterface questionModelForDelete = this.questionSupport.getModelFixtureMock(0);

        final QuestionsOptionsInterface questionOptionForAuth = new QuestionsOptions();
        final QuestionsOptionsInterface questionOptionForDelete = new QuestionsOptions();

        when(this.questionsOptionsBuilder.forAuth()).thenReturn(questionOptionForAuth);
        when(this.questionsOptionsBuilder.forDelete()).thenReturn(questionOptionForDelete);

        when(this.questionsService.find(any(Integer.class), eq(questionOptionForAuth)))
                .thenReturn(questionModelForAuth);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(true);
        when(this.questionsService.find(any(Integer.class), eq(questionOptionForDelete)))
                .thenReturn(questionModelForDelete);

        this.mvc.perform(delete("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();

        verify(this.questionsService, times(1)).delete(questionModelForDelete,
                questionOptionForDelete);
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        final QuestionModelInterface questionModel = this.questionSupport.getModelFixtureMock(0);
        final QuestionsOptionsInterface questionOption = new QuestionsOptions();

        when(this.questionsOptionsBuilder.forAuth()).thenReturn(questionOption);
        when(this.questionsService.find(any(Integer.class), eq(questionOption)))
                .thenReturn(questionModel);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(false);

        mvc.perform(delete("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        final QuestionsOptionsInterface questionOption = new QuestionsOptions();

        when(this.questionsOptionsBuilder.forAuth()).thenReturn(questionOption);
        when(this.questionsService.find(any(Integer.class), eq(questionOption))).thenReturn(null);

        mvc.perform(delete("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

}
