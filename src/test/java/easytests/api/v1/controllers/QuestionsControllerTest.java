package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuestionsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.builder.QuestionsOptionsBuilder;
import easytests.core.options.QuestionsOptions;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.services.QuestionsService;
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

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionsService questionsService;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private QuestionsOptionsBuilder questionsOptionsBuilder;

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
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);
        when(this.questionsService.find(any(), any())).thenReturn(questionModel);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(true);
        final ArgumentCaptor<QuestionModelInterface> argumentCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);
        this.mvc.perform(delete("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
        verify(this.questionsService, times(1)).delete(argumentCaptor.capture());
        this.questionsSupport.assertEquals(argumentCaptor.getValue(), questionModel);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        when(this.questionsService.find(any(Integer.class))).thenReturn(null);

        mvc.perform(delete("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(this.questionsSupport.getEntityFixtureMock(0));
        when(this.questionsService.find(any(), any())).thenReturn(questionModel);
        when(this.acl.hasAccess(any(QuestionModelInterface.class))).thenReturn(false);

        mvc.perform(delete("/v1/questions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }
}
