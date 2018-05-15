package easytests.api.v1.controllers;

import easytests.api.v1.mappers.IssuesMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.support.IssueSupport;
import easytests.support.JsonSupport;
import easytests.support.SubjectsSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
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
 * @author Yarik2308
 */
@Import({IssuesMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IssuesController.class, secure = false)
public class IssuesControllerTest {
    private static String id = "id";
    private static String name = "name";
    private static String subject = "subject";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IssuesServiceInterface issuesService;

    @MockBean
    private SubjectsServiceInterface subjectsService;

    @MockBean
    private IssuesMapper issuesMapper;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private IssuesOptionsBuilder issuesOptionsBuilder;

    private IssueSupport issueSupport = new IssueSupport();

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    /**
     * list
     */
    /**
     * create
     */

    @Test
    public void testUpdateSuccess() throws Exception {
        final IssueModelInterface issueModel = issueSupport.getModelFixtureMock(0);

        when(this.issuesService.find(any(), any())).thenReturn(issueModel);
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(true);

        mvc.perform(put("/v1/issues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 1)
                        .with(name, "newIssue")
                        .with(subject, new JsonSupport().with(id, 2))
                        .build()
                ))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();

        verify(this.issuesService, times(1)).save(issueModel);
    }

    /**
     * show(issueId)
     */
    /**
     * delete(issueId)
     */

}
