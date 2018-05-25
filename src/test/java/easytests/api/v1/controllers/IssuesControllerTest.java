package easytests.api.v1.controllers;

import easytests.api.v1.mappers.IssuesMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.support.IssueSupport;
import easytests.support.JsonSupport;
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
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private IssuesOptionsBuilder issuesOptionsBuilder;

    private IssueSupport issueSupport = new IssueSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<IssueModelInterface> issuesModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final IssueModel issueModel = new IssueModel();
            issueModel.map(this.issueSupport.getEntityFixtureMock(idx));
            issuesModels.add(issueModel);
        });

        int subjectIdParamValue = 1;

        when(this.subjectsService.find(subjectIdParamValue))
                .thenReturn(new SubjectModelEmpty(subjectIdParamValue));
        when(this.issuesService.findBySubject(new SubjectModelEmpty(subjectIdParamValue)))
                .thenReturn(issuesModels);
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(true);

        this.mvc.perform(get("/v1/issues?subjectId={subjectIdParamValue}", subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, issuesModels.get(0).getId())
                                .with(name, issuesModels.get(0).getName())
                                .with(subject, new JsonSupport().with(id, issuesModels.get(0).getSubject().getId())))
                        .with(new JsonSupport()
                                .with(id, issuesModels.get(1).getId())
                                .with(name, issuesModels.get(1).getName())
                                .with(subject, new JsonSupport().with(id, issuesModels.get(1).getSubject().getId())))
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testListNotFound() throws Exception {
        int subjectIdParamValue = 5;

        this.mvc.perform(get("/v1/issues?subjectId={subjectIdParamValue}", subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {
        int subjectIdParamValue = 1;

        when(this.subjectsService.find(subjectIdParamValue))
                .thenReturn(new SubjectModelEmpty(subjectIdParamValue));
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/issues?subjectId={subjectIdParamValue}", subjectIdParamValue)
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
    @Test
    public void testDeleteSuccess() throws Exception {
        final ArgumentCaptor<IssueModelInterface> issueModelArgumentCaptor = ArgumentCaptor.forClass(IssueModelInterface.class);
        final IssueModelInterface issueModel = this.issueSupport.getModelFixtureMock(0);
        when(this.issuesService.find(any(), any())).thenReturn(issueModel);
        when(this.acl.hasAccess(any(IssueModelInterface.class))).thenReturn(true);
        this.mvc.perform(delete("/v1/issues/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
        verify(this.issuesService, times(1)).delete(issueModelArgumentCaptor.capture());
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        final IssueModel issueModel = new IssueModel();
        issueModel.map(this.issueSupport.getEntityFixtureMock(0));
        when(this.issuesService.find(any(), any())).thenReturn(issueModel);
        when(this.acl.hasAccess(any(IssueModelInterface.class))).thenReturn(false);
        this.mvc.perform(delete("/v1/issues/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        when(this.acl.hasAccess(any(IssueModelInterface.class))).thenReturn(true);
        this.mvc.perform(delete("/v1/issues/5")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

}
