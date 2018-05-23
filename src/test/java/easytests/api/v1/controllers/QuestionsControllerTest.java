package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuestionsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.models.empty.QuestionModelEmpty;
import easytests.core.models.empty.TopicModelEmpty;
import easytests.core.models.empty.QuestionTypeModelEmpty;
import easytests.core.models.empty.AnswerModelEmpty;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.options.builder.AnswersOptionsBuilder;
import easytests.core.services.QuestionsService;
import easytests.core.services.QuestionTypesService;
import easytests.core.services.AnswersService;
import easytests.core.services.TopicsService;
import easytests.support.QuestionsSupport;
import easytests.support.AnswersSupport;
import easytests.support.JsonSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    private static String idAdminAnswer = "id";
    private static String textAdminAnswer = "text";
    private static String isRight = "isRight";
    private static String number = "number";
    private static String question = "question";


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
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    //@MockBean
    //private AnswersOptionsBuilder answersOptionsBuilder;

    private QuestionsSupport questionSupport = new QuestionsSupport();

    private AnswersSupport answersSupport = new AnswersSupport();

    @Test
    public void testListSuccess() throws Exception {
        /*final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        ///final List<AnswerModelInterface> answersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final QuestionModel questionModel = new QuestionModel();
            questionModel.map(this.questionSupport.getEntityFixtureMock(idx));
            questionsModels.add(questionModel);

            //final AnswerModel answerModel=new AnswerModel();
            //answerModel.map(this.answersSupport.getEntityFixtureMock(idx));
            //answersModels.add(answerModel);
        });*/

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        IntStream.range(0, 3).forEach(idx -> {
            final QuestionModel questionModel = new QuestionModel();
            questionModel.map(this.questionSupport.getEntityFixtureMock(idx));

            final List<AnswerModelInterface> answersModels = new ArrayList<>();
            IntStream.range(0, 3).forEach(answerIdx ->{
                final AnswerModel answerModel = new AnswerModel();
                answerModel.map(answersSupport.getEntityFixtureMock(answerIdx));
                answerModel.setQuestion(questionModel);
                answersModels.add(answerModel);
            });

            questionModel.setAnswers(answersModels);

            if (questionModel.getId().equals(1)) {
                questionsModels.add(questionModel);
            }

        });

        int topicIdParamValue = 1;
/*
        //when(this.questionTypesService.find(topicIdParamValue))
        //        .thenReturn(new QuestionTypeModelEmpty(topicIdParamValue));
        when(this.topicsService.find(topicIdParamValue))
                .thenReturn(new TopicModelEmpty(topicIdParamValue));
        //when(this.answersService.find(topicIdParamValue))
        //        .thenReturn(new AnswerModelEmpty(topicIdParamValue));
        when(this.questionsService.findByTopic(new TopicModelEmpty(topicIdParamValue)))
                .thenReturn(questionsModels);
        //when(this.answersService.findByQuestion(new QuestionModelEmpty(topicIdParamValue)))
        //        .thenReturn(answersModels);
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);*/

        when(this.topicsService.find(topicIdParamValue))
                .thenReturn(new TopicModelEmpty(topicIdParamValue));
        when(this.questionTypesService.find(topicIdParamValue))
                .thenReturn(new QuestionTypeModelEmpty(topicIdParamValue));
        when(this.answersService.find(any(Integer.class))).thenReturn(new AnswerModelEmpty(topicIdParamValue));
        when(this.questionsService.findByTopic(any(TopicModelInterface.class))).thenReturn(questionsModels);
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);

        this.mvc.perform(get("/v1/questions?topicId={topicIdParamValue}", topicIdParamValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, questionsModels.get(0).getId())
                                .with(text, questionsModels.get(0).getText())
                                .with(type, new JsonSupport().with(id, questionsModels.get(0).getQuestionType().getId()))
                                .with(topic, new JsonSupport().with(id, questionsModels.get(0).getTopic().getId()))
                                /*//.with(answers, questionsModels.get(0).getAnswers())
                                .with(idAdminAnswer, questionsModels.get(0).getAnswers().get(0).getId())
                                .with(textAdminAnswer, questionsModels.get(0).getAnswers().get(0).getTxt())
                                .with(isRight, questionsModels.get(0).getAnswers().get(0).getRight())
                                .with(number, questionsModels.get(0).getAnswers().get(0).getSerialNumber())*/
                                .with(answers, new JsonSupport()
                                        .with(id, questionsModels.get(0).getAnswers().get(0).getId())
                                        .with(text, questionsModels.get(0).getAnswers().get(0).getTxt())
                                        .with(isRight, questionsModels.get(0).getAnswers().get(0).getRight())
                                        .with(number, questionsModels.get(0).getAnswers().get(0).getSerialNumber())
                                        .with(question, new JsonSupport().with(id, questionsModels.get(0).getId())))
                        )
                                //.with(answers, new JsonSupport().with(id, questionsModels.get(0).getAnswers().get(0).getId()))                        )
                        .with(new JsonSupport()
                                .with(id, questionsModels.get(1).getId())
                                .with(text, questionsModels.get(1).getText())
                                .with(type, new JsonSupport().with(id, questionsModels.get(1).getQuestionType().getId()))
                                .with(topic, new JsonSupport().with(id, questionsModels.get(1).getTopic().getId()))
                                /*.with(idAdminAnswer, questionsModels.get(1).getAnswers().get(0).getId())
                                .with(textAdminAnswer, questionsModels.get(1).getAnswers().get(0).getTxt())
                                .with(isRight, questionsModels.get(1).getAnswers().get(0).getRight())
                                .with(number, questionsModels.get(1).getAnswers().get(0).getSerialNumber())*/
                                .with(answers, new JsonSupport()
                                        .with(id, questionsModels.get(1).getAnswers().get(0).getId())
                                        .with(text, questionsModels.get(1).getAnswers().get(0).getTxt())
                                        .with(isRight, questionsModels.get(1).getAnswers().get(0).getRight())
                                        .with(number, questionsModels.get(1).getAnswers().get(0).getSerialNumber())
                                        .with(question, new JsonSupport().with(id, questionsModels.get(0).getId())))
                        )
                                //.with(answers, new JsonSupport().with(id, questionsModels.get(1).getAnswers().get(0).getId())))
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testListNotFound() throws Exception {
        int topicIdParamValue = 5;

        this.mvc.perform(get("/v1/questions?topicId={topicIdParamValue}", topicIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {
        int topicIdParamValue = 1;

        when(this.topicsService.find(topicIdParamValue))
                .thenReturn(new TopicModelEmpty(topicIdParamValue));
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/questions?topicId={topicIdParamValue}", topicIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();

    }

    /**
     * create
     */
    /**
     * update
     */
    /**
     * show(issueId)
     */
    /**
     * delete(issueId)
     */
}
