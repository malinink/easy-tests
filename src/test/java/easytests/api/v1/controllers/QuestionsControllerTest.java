package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuestionsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.models.AnswerModelInterface;
import easytests.core.options.AnswersOptions;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.options.QuestionsOptions;
import easytests.core.options.builder.QuestionsOptionsBuilder;
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
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    private QuestionsSupport questionSupport = new QuestionsSupport();

    private AnswersSupport answersSupport = new AnswersSupport();

    /**
     * list
     */
    /**
     * create
     */
    /**
     * update
     */

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
        when(this.questionsOptionsBuilder.forAuth()).thenReturn(new QuestionsOptions().withAnswers(new AnswersOptions()));
        when(this.questionsService.find(any(Integer.class), any(QuestionsOptionsInterface.class))).thenReturn(questionModel);
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
        when(this.questionsService.find(any(Integer.class), any(QuestionsOptionsInterface.class))).thenReturn(null);

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
        when(this.questionsService.find(any(Integer.class), any(QuestionsOptionsInterface.class))).thenReturn(questionModel);
        when(this.acl.hasAccess(any(UserModelInterface.class))).thenReturn(false);

        mvc.perform(get("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    /**
     * delete(questionId)
     */

}
