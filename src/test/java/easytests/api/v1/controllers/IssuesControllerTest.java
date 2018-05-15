package easytests.api.v1.controllers;

import easytests.api.v1.mappers.IssuesMapper;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesService;
import easytests.support.IssueSupport;
import easytests.support.JsonSupport;
import easytests.support.SubjectsSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    private static String subjectId = "subjectId";
    private static int subjectIdParamValue = 1;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IssuesService issuesService;

    @MockBean
    private IssuesOptionsBuilder issuesOptionsBuilder;

    private IssueSupport issueSupport = new IssueSupport();

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<IssueModelInterface> issuesModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final IssueModel issueModel = new IssueModel();
            issueModel.map(this.issueSupport.getEntityFixtureMock(idx));
            issuesModels.add(issueModel);
        });

        when(this.issuesService.findBySubject(new SubjectModelEmpty(subjectIdParamValue))).thenReturn(issuesModels);

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
