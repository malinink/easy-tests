package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.mappers.QuestionsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.AnswerModel;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.services.QuestionsService;
import easytests.support.AnswersSupport;
import easytests.support.JsonSupport;
import easytests.support.QuestionsSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static String isRight = "isRight";
    private static String number = "number";
    private static String answers = "answers";
    private static String topic = "topic";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionsService questionsService;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    private AnswersSupport answersSupport = new AnswersSupport();

    /**
     * list
     */
    /**
     * create
     */

    @Test
    public void testCreateSuccess() throws Exception {
        doAnswer(invocation -> {
            final QuestionModel questionModel = (QuestionModel) invocation.getArguments()[0];
            questionModel.setId(5);
            return null;
        }).when(this.questionsService).save(any(QuestionModelInterface.class));
        final ArgumentCaptor<QuestionModelInterface> questionModelCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);
        mvc.perform(post("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(text, "Question")
                        .with(type, 1)
                        .with(topic, new JsonSupport().with(id, 1))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(text, "Answer1text")
                                        .with(isRight, false)
                                        .with(number, 1))
                                .with(new JsonSupport()
                                        .with(text, "Answer2text")
                                        .with(isRight, true)
                                        .with(number, 2))
                                .with(new JsonSupport()
                                        .with(text, "Answer3text")
                                        .with(isRight, false)
                                        .with(number, 3))
                                .with(new JsonSupport()
                                        .with(text, "Answer4text")
                                        .with(isRight, false)
                                        .with(number, 4)))
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
        verify(this.questionsService, times(1)).save(questionModelCaptor.capture());

    }

    @Test
    public void testCreateWithIdFailed() throws Exception {
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
                                        .with(number, 1))
                                .with(new JsonSupport()
                                        .with(text, "Answer2text")
                                        .with(isRight, true)
                                        .with(number, 2))
                                .with(new JsonSupport()
                                        .with(text, "Answer3text")
                                        .with(isRight, false)
                                        .with(number, 3))
                                .with(new JsonSupport()
                                        .with(text, "Answer4text")
                                        .with(isRight, false)
                                        .with(number, 4)))
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
    public void testCreateWithAnswerValidationFailed() throws Exception {
        mvc.perform(post("/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(text, "Question2text")
                        .with(type, 1)
                        .with(topic, new JsonSupport().with(id, 1))
                        .with(answers, new JsonSupport()
                                .with(new JsonSupport()
                                        .with(text, "Answer1text")
                                        .with(isRight, false)
                                        .with(number, 1))
                                .with(new JsonSupport()
                                        .with(text, "Answer2text")
                                        .with(isRight, true)
                                        .with(number, 2))
                                .with(new JsonSupport()
                                        .with(text, "Answer3text")
                                        .with(isRight, true)
                                        .with(number, 3))
                                .with(new JsonSupport()
                                        .with(text, "Answer4text")
                                        .with(isRight, false)
                                        .with(number, 4)))
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

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
