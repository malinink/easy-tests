package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuestionsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.AnswerModel;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.builder.AnswersOptionsBuilder;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.options.AnswersOptions;
import easytests.core.options.AnswersOptionsInterface;
import easytests.core.options.QuestionsOptions;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.services.AnswersService;
import easytests.core.services.QuestionsService;
import easytests.support.AnswersSupport;
import easytests.support.QuestionsSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
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

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnswersService answersService;

    @MockBean
    private QuestionsService questionsService;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @MockBean
    private AnswersOptionsBuilder answersOptionsBuilder;

    private AnswersSupport answersSupport = new AnswersSupport();

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    /**
     * list
     */
    /**
     * create
     */
    /**
     * update
     */
    /**
     * show(issueId)
     */
    @Test
    public void testDeleteSuccess() throws Exception {
        final QuestionModelInterface questionModelForAuth = this.questionsSupport.getModelFixtureMock(0);
        final QuestionModelInterface questionModelForDelete = this.questionsSupport.getModelFixtureMock(0);

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
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);
        final QuestionsOptionsInterface questionOption = new QuestionsOptions();

        when(this.questionsOptionsBuilder.forAuth()).thenReturn(questionOption);
        when(this.questionsService.find(any(Integer.class), eq(questionOption))).
                thenReturn(questionModel);
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
