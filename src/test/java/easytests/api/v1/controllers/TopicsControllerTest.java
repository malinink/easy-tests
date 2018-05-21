package easytests.api.v1.controllers;

import easytests.api.v1.mappers.TopicsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.TopicsOptionsBuilderInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import easytests.support.JsonSupport;
import easytests.support.TopicsSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lelay
 */
@Import({TopicsMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TopicsController.class, secure = false)
public class TopicsControllerTest {
    private static final String id = "id";
    private static final String name = "name";
    private static final String subject = "subject";
    private static final String subjectIdParamValue = "2";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TopicsServiceInterface topicsService;

    @MockBean
    private SubjectsServiceInterface subjectsService;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @Autowired
    @Qualifier("TopicsMapperV1")
    private TopicsMapper topicsMapper;

    @MockBean
    TopicsOptionsBuilderInterface topicsOptionsBuilder;

    private TopicsSupport topicsSupport = new TopicsSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final TopicModel topicModel = new TopicModel();
            topicModel.map(this.topicsSupport.getEntityFixtureMock(idx));
            topicsModels.add(topicModel);
        });
        when(this.topicsService.findBySubject(any(SubjectModelInterface.class))).thenReturn(topicsModels);

        when(this.subjectsService.find(Integer.valueOf(subjectIdParamValue)))
                .thenReturn(new SubjectModelEmpty(Integer.valueOf(subjectIdParamValue)));
        when(this.acl.hasAccess(any(SubjectModelInterface.class)))
                .thenReturn(true);

        final String receivedModelsJson = new JsonSupport()
                .with(new JsonSupport()
                        .with(id, topicsModels.get(0).getId())
                        .with(name, topicsModels.get(0).getName())
                        .with(subject, new JsonSupport()
                                .with(id, topicsModels.get(0).getSubject().getId())
                        )
                )
                .with(new JsonSupport()
                        .with(id, topicsModels.get(1).getId())
                        .with(name, topicsModels.get(1).getName())
                        .with(subject, new JsonSupport()
                                .with(id, topicsModels.get(0).getSubject().getId())
                        )
                )
                .build();

        mvc.perform(get("/v1/topics?subjectId={subjectIdParamValue}", subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(receivedModelsJson))
                .andReturn();

    }

    @Test
    public void testListNotFound() throws Exception {
        final String nonexistentSubjectId = "5";

        this.mvc.perform(get("/v1/topics?subjectId={nonexistentSubjectId}", nonexistentSubjectId)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {

        when(this.subjectsService.find(Integer.valueOf(subjectIdParamValue)))
                .thenReturn(new SubjectModelEmpty(Integer.valueOf(subjectIdParamValue)));
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/topics?subjectId={subjectIdParamValue}", subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();

    }
}
