package easytests.api.v1.controllers;

import easytests.api.v1.mappers.QuizzesMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.models.empty.IssueModelEmpty;
import easytests.core.options.QuizzesOptions;
import easytests.core.options.QuizzesOptionsInterface;
import easytests.core.options.builder.QuizzesOptionsBuilder;
import easytests.core.services.IssuesService;
import easytests.core.services.QuizzesService;
import easytests.support.JsonSupport;
import easytests.support.QuizzesSupport;
import easytests.support.TesteesSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author miron97
 */
@Import({QuizzesMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = QuizzesController.class, secure = false)
public class QuizzesControllerTest {
    private static String id = "id";
    private static String issue = "issue";
    private static String inviteCode = "inviteCode";
    private static String codeExpired = "codeExpired";
    private static String startedAt = "startedAt";
    private static String finishedAt = "finishedAt";
    private static String testee = "testee";
    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String surname = "surname";
    private static String groupNumber = "groupNumber";
    private static String quiz = "quiz";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuizzesService quizzesService;

    @MockBean
    private IssuesService issuesService;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private QuizzesOptionsBuilder quizzesOptionsBuilder;

    private QuizzesSupport quizzesSupport = new QuizzesSupport();
    private TesteesSupport testeesSupport = new TesteesSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.map(testeesSupport.getEntityFixtureMock(0));

        IntStream.range(0, 2).forEach(idx -> {
            final QuizModel quizModel = new QuizModel();
            quizModel.map(this.quizzesSupport.getEntityFixtureMock(idx));

            if (quizModel.getId().equals(2)) {
                quizModel.setTestee(testeeModel);
            }

            quizzesModels.add(quizModel);
        });

        int issueIdParamValue = 1;


        when(this.issuesService.find(any(Integer.class))).thenReturn(new IssueModelEmpty(issueIdParamValue));
        when(this.quizzesService.findByIssue(any(IssueModelInterface.class))).thenReturn(quizzesModels);
        when(this.acl.hasAccess(any(IssueModelInterface.class))).thenReturn(true);

        this.mvc.perform(get("/v1/quizzes?issueId={issueIdParamValue}", issueIdParamValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, quizzesModels.get(0).getId())
                                .with(issue, new JsonSupport().with(id, quizzesModels.get(0).getIssue().getId()))
                                .with(inviteCode, quizzesModels.get(0).getInviteCode())
                                .with(codeExpired, quizzesModels.get(0).getCodeExpired())
                                .with(startedAt, quizzesModels.get(0).getStartedAt())
                                .with(finishedAt, quizzesModels.get(0).getFinishedAt()))
                        .with(new JsonSupport()
                                .with(id, quizzesModels.get(1).getId())
                                .with(issue, new JsonSupport().with(id, quizzesModels.get(1).getIssue().getId()))
                                .with(inviteCode, quizzesModels.get(1).getInviteCode())
                                .with(codeExpired, quizzesModels.get(1).getCodeExpired())
                                .with(startedAt, quizzesModels.get(1).getStartedAt())
                                .with(finishedAt, quizzesModels.get(1).getFinishedAt())
                                .with(testee, new JsonSupport()
                                        .with(id, testeeModel.getId())
                                        .with(firstName, testeeModel.getFirstName())
                                        .with(lastName, testeeModel.getLastName())
                                        .with(surname, testeeModel.getSurname())
                                        .with(groupNumber, testeeModel.getGroupNumber())
                                        .with(quiz, new JsonSupport().with(id, testeeModel.getQuiz().getId()))))
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testListNotFound() throws Exception {
        int issueIdParamValue = 10;

        this.mvc.perform(get("/v1/quizzes?issueId={issueIdParamValue}", issueIdParamValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {
        int issueIdParamValue = 10;

        when(this.issuesService.find(issueIdParamValue))
                .thenReturn(new IssueModelEmpty(issueIdParamValue));
        when(this.acl.hasAccess(any(IssueModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/quizzes?issueId={issueIdParamValue}", issueIdParamValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }
    /**
     * show(quizId)
     */

    @Test
    public void testShowSuccess() throws Exception {
        final QuizModelInterface quizModel = new QuizModel();
        quizModel.map(this.quizzesSupport.getEntityFixtureMock(0));
        when(this.quizzesOptionsBuilder.forAuth()).thenReturn(new QuizzesOptions());
        when(this.quizzesService.find(any(Integer.class), any(QuizzesOptionsInterface.class))).thenReturn(quizModel);
        when(this.acl.hasAccess(any(QuizModelInterface.class))).thenReturn(true);

        mvc.perform(get("/v1/quizzes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(id, quizModel.getId())
                        .with(issue, new JsonSupport().with(id, quizModel.getIssue().getId()))
                        .with(inviteCode, quizModel.getInviteCode())
                        .with(codeExpired, quizModel.getCodeExpired())
                        .with(startedAt, quizModel.getStartedAt())
                        .with(finishedAt, quizModel.getFinishedAt())
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testShowNotFound() throws Exception {
        when(this.quizzesOptionsBuilder.forAuth()).thenReturn(new QuizzesOptions());
        when(this.quizzesService.find(any(Integer.class), any(QuizzesOptionsInterface.class))).thenReturn(null);

        mvc.perform(get("/v1/quizzes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowForbidden() throws Exception {
        final QuizModelInterface quizModel = new QuizModel();
        quizModel.map(this.quizzesSupport.getEntityFixtureMock(0));
        when(this.quizzesOptionsBuilder.forAuth()).thenReturn(new QuizzesOptions());
        when(this.quizzesService.find(any(Integer.class), any(QuizzesOptionsInterface.class))).thenReturn(quizModel);
        when(this.acl.hasAccess(any(QuizModelInterface.class))).thenReturn(false);

        mvc.perform(get("/v1/quizzes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowWithTesteeSuccess() throws Exception {
        final QuizModelInterface quizModel = new QuizModel();
        quizModel.map(this.quizzesSupport.getEntityFixtureMock(0));
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.map(this.testeesSupport.getEntityFixtureMock(0));
        quizModel.setTestee(testeeModel);
        when(this.quizzesOptionsBuilder.forAuth()).thenReturn(new QuizzesOptions());
        when(this.quizzesService.find(any(Integer.class), any(QuizzesOptionsInterface.class))).thenReturn(quizModel);
        when(this.acl.hasAccess(any(QuizModelInterface.class))).thenReturn(true);

        mvc.perform(get("/v1/quizzes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(id, quizModel.getId())
                        .with(issue, new JsonSupport().with(id, quizModel.getIssue().getId()))
                        .with(inviteCode, quizModel.getInviteCode())
                        .with(codeExpired, quizModel.getCodeExpired())
                        .with(startedAt, quizModel.getStartedAt())
                        .with(finishedAt, quizModel.getFinishedAt())
                                .with(testee, new JsonSupport()
                                        .with(id, testeeModel.getId())
                                        .with(firstName, testeeModel.getFirstName())
                                        .with(lastName, testeeModel.getLastName())
                                        .with(surname, testeeModel.getSurname())
                                        .with(groupNumber, testeeModel.getGroupNumber())
                                )
                        .build()
                ))
                .andReturn();
    }
}
